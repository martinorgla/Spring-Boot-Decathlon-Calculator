package com.industry62.decathlonrestservice;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DecathlonService {
    /*
     * Get sport parameters
     */
    public Collection<SportDto> getSports() {
        return Arrays.stream(DecathlonEvent.values())
                .map(decathlonEvent -> new SportDto(decathlonEvent.name(), decathlonEvent.getName()))
                .collect(Collectors.toList());
    }

    /*
     * Calculate points
     */
    public DecathlonResultDto calculatePoints (List<EventResultDto> eventResults) {
        for (int i = 0; i < eventResults.size(); i++) {
            eventResults.get(i).setPoints(calculatePointsBySport(eventResults.get(i)));
        }

        return new DecathlonResultDto(eventResults);
    }

    /*
     * Calculate points by Sport
     */
    private int calculatePointsBySport (EventResultDto eventResultDto) {
        try {
            DecathlonEvent decathlonEvent = DecathlonEvent.valueOf(eventResultDto.getEventId());

            if (decathlonEvent.getSportType().equals(SportType.TRACK)) {
                return this.calculateTrackPoints(decathlonEvent, eventResultDto);
            }
            else {
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
    private int calculateTrackPoints (DecathlonEvent decathlonEvent, EventResultDto eventResultDto) {
        return (int) (decathlonEvent.getA() * Math.pow(decathlonEvent.getB() - eventResultDto.getResult(), decathlonEvent.getC()));
    }

    /*
     * Calculate field points
     *
     * Formula A*(P–B)C;
     */
    private int calculateFieldPoints (DecathlonEvent decathlonEvent, EventResultDto eventResultDto) {
       return (int) (decathlonEvent.getA()*Math.pow(eventResultDto.getResult()-decathlonEvent.getB(), decathlonEvent.getC()));
    }
}
