package com.industry62.decathlonrestservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@Service
public class DecathlonService {

    private static Map<Integer, DecathlonResultDto> results = new HashMap<>();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*
     * Get sport parameters
     */
    public Collection<SportDto> getSports() {
        return Arrays.stream(DecathlonEvent.values())
                .map(decathlonEvent -> new SportDto(decathlonEvent.name(), decathlonEvent.getName()))
                .collect(Collectors.toList());
    }

    public Collection<DecathlonResultDto> getResults() {
        return results.values();
    }

    /*
     * Calculate points
     */
    public DecathlonResultDto calculatePoints(List<EventResultDto> eventResults) {
        for (EventResultDto eventResult : eventResults) {
            eventResult.setPoints(calculatePointsBySport(eventResult));
        }

        DecathlonResultDto result = new DecathlonResultDto(eventResults);
        results.put(result.getPoints(), result);

        this.logDecathlonResult(result);

        return result;
    }

    private void logDecathlonResult(DecathlonResultDto result) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Timestamp timestamp = new Timestamp(new Date().getTime());

        if (result.getPoints() > 0) {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO result (points, date) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);

                ps.setInt(1, result.getPoints());
                ps.setTimestamp(2, timestamp);

                return ps;
            }, keyHolder);

            if (keyHolder.getKey().intValue() > 0) {
                for (EventResultDto eventResult : result.getEventResultDto()) {
                    this.logDecathlonEventResult(keyHolder.getKey().intValue(), eventResult);
                }
            }
        }
    }

    private void logDecathlonEventResult (Integer result_id, EventResultDto result) {
        jdbcTemplate.update("INSERT INTO event_result (result_id, eventId, result, points) VALUES (?, ?, ?, ?)", result_id, result.getEventId(), result.getResult(), result.getPoints());
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
    private int calculateFieldPoints (DecathlonEvent decathlonEvent, EventResultDto eventResultDto) {
        if (eventResultDto.getResult() == 0) {
            return 0;
        }

        return (int) (decathlonEvent.getA()*Math.pow(eventResultDto.getResult()-decathlonEvent.getB(), decathlonEvent.getC()));
    }
}
