package com.industry62.decathlon.service;

import com.industry62.decathlon.dto.DecathlonResultDto;
import com.industry62.decathlon.dto.EventResultDto;
import com.industry62.decathlon.dto.SportDto;
import com.industry62.decathlon.enums.DecathlonEvent;
import com.industry62.decathlon.enums.SportType;
import com.industry62.decathlon.repository.DecathlonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DecathlonService {
    @Autowired
    private DecathlonRepository decathlonRepository;

    private static Map<Integer, DecathlonResultDto> results = new HashMap<>();

    /*
     * Get sport parameters
     */
    public Collection<SportDto> getSports() {
        return Arrays.stream(DecathlonEvent.values())
                .map(decathlonEvent -> new SportDto(decathlonEvent.name(), decathlonEvent.getName()))
                .collect(Collectors.toList());
    }

    /*
     * Get results
     */
    public Collection<DecathlonResultDto> getResults() {
        return results.values();
    }

    /*
     * Calculate points
     */
    public DecathlonResultDto storeAndCalculatePoints(List<EventResultDto> eventResults) {
        DecathlonResultDto result = calculatePoints(eventResults);
        logResult(result);

        return result;
    }

    /*
     * Calculate points
     */
    public DecathlonResultDto calculatePoints(List<EventResultDto> eventResults) {
        for (EventResultDto eventResult : eventResults) {
            eventResult.setPoints(calculatePointsBySport(eventResult));
        }

        DecathlonResultDto result = new DecathlonResultDto();
        result.setEventResults(eventResults);
        result.setPoints(eventResults.stream().mapToInt(er -> er.getPoints()).sum());
        results.put(result.getPoints(), result);

        return result;
    }

    /*
     * Log result
     */
    private void logResult(DecathlonResultDto result) {
        if (result.getPoints() > 0) {
            decathlonRepository.storeDecathlonResult(result);
        }
    }

    /*
     * Calculate points by Sport
     */
    private int calculatePointsBySport(EventResultDto eventResultDto) {
        try {
            DecathlonEvent decathlonEvent = DecathlonEvent.valueOf(eventResultDto.getEventId());

            if (decathlonEvent.getSportType().equals(SportType.TRACK)) {
                return this.calculateTrackPoints(decathlonEvent, eventResultDto);
            } else {
                return this.calculateFieldPoints(decathlonEvent, eventResultDto);
            }
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Unknown sport " + eventResultDto.getEventId());
        }
    }

    /*
     * Calculate track points
     *
     * Formula A*(B–P)C;
     */
    private int calculateTrackPoints(DecathlonEvent decathlonEvent, EventResultDto eventResultDto) {
        if (eventResultDto.getResult() == 0) {
            return 0;
        }

        return (int) (decathlonEvent.getA() * Math.pow(decathlonEvent.getB() - eventResultDto.getResult(), decathlonEvent.getC()));
    }

    /*
     * Calculate field points
     *
     * Formula A*(P–B)C;
     */
    private int calculateFieldPoints(DecathlonEvent decathlonEvent, EventResultDto eventResultDto) {
        if (eventResultDto.getResult() == 0) {
            return 0;
        }

        return (int) (decathlonEvent.getA() * Math.pow(eventResultDto.getResult() - decathlonEvent.getB(), decathlonEvent.getC()));
    }
}
