package com.industry62.decathlonrestservice;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class DecathlonServiceTest {
    private List<EventResultDto> eventResults = new ArrayList<>();

    private DecathlonServiceTest () {
        eventResults.add(new EventResultDto("M100", 10.395));
        eventResults.add(new EventResultDto("LONG_JUMP", 776));
        eventResults.add(new EventResultDto("SHOT_PUT", 18.4));
        eventResults.add(new EventResultDto("HIGH_JUMP", 220));
        eventResults.add(new EventResultDto("M400", 46.17));
        eventResults.add(new EventResultDto("M110_HURDLES", 13.8));
        eventResults.add(new EventResultDto("DISCUS_THROW", 56.17));
        eventResults.add(new EventResultDto("POLE_VAULT", 528));
        eventResults.add(new EventResultDto("JAVELIN_THROW", 77.19));
        eventResults.add(new EventResultDto("M1500", 233.79));
    }

    @Test
    public void calculate_M100_points () {
        final DecathlonService decathlonService = new DecathlonService();

        DecathlonResultDto decathlonResultDto = decathlonService.calculatePoints(eventResults);

        int points = decathlonResultDto
                .getEventResultDto()
                .stream()
                .filter(event -> event.getEventId()
                        .equals("M100"))
                .mapToInt(event -> event.getPoints())
                .sum();

        assertEquals(1000, points);
    }

    @Test
    public void calculate_LONG_JUMP_points () {
        final DecathlonService decathlonService = new DecathlonService();

        DecathlonResultDto decathlonResultDto = decathlonService.calculatePoints(eventResults);

        int points = decathlonResultDto
                .getEventResultDto()
                .stream()
                .filter(event -> event.getEventId()
                        .equals("LONG_JUMP"))
                .mapToInt(event -> event.getPoints())
                .sum();

        assertEquals(1000, points);
    }

    @Test
    public void calculate_SHOT_PUT_points () {
        final DecathlonService decathlonService = new DecathlonService();

        DecathlonResultDto decathlonResultDto = decathlonService.calculatePoints(eventResults);

        int points = decathlonResultDto
                .getEventResultDto()
                .stream()
                .filter(event -> event.getEventId()
                        .equals("SHOT_PUT"))
                .mapToInt(event -> event.getPoints())
                .sum();

        assertEquals(1000, points);
    }

    @Test
    public void calculate_HIGH_JUMP_points () {
        final DecathlonService decathlonService = new DecathlonService();

        DecathlonResultDto decathlonResultDto = decathlonService.calculatePoints(eventResults);

        int points = decathlonResultDto
                .getEventResultDto()
                .stream()
                .filter(event -> event.getEventId()
                        .equals("HIGH_JUMP"))
                .mapToInt(event -> event.getPoints())
                .sum();

        assertEquals(992, points);
    }

    @Test
    public void calculate_M400_points () {
        final DecathlonService decathlonService = new DecathlonService();

        DecathlonResultDto decathlonResultDto = decathlonService.calculatePoints(eventResults);

        int points = decathlonResultDto
                .getEventResultDto()
                .stream()
                .filter(event -> event.getEventId()
                        .equals("M400"))
                .mapToInt(event -> event.getPoints())
                .sum();

        assertEquals(1000, points);
    }

    @Test
    public void calculate_M110_HURDLES_points () {
        final DecathlonService decathlonService = new DecathlonService();

        DecathlonResultDto decathlonResultDto = decathlonService.calculatePoints(eventResults);

        int points = decathlonResultDto
                .getEventResultDto()
                .stream()
                .filter(event -> event.getEventId()
                        .equals("M110_HURDLES"))
                .mapToInt(event -> event.getPoints())
                .sum();

        assertEquals(1000, points);
    }

    @Test
    public void calculate_DISCUS_THROW_points () {
        final DecathlonService decathlonService = new DecathlonService();

        DecathlonResultDto decathlonResultDto = decathlonService.calculatePoints(eventResults);

        int points = decathlonResultDto
                .getEventResultDto()
                .stream()
                .filter(event -> event.getEventId()
                        .equals("DISCUS_THROW"))
                .mapToInt(event -> event.getPoints())
                .sum();

        assertEquals(1000, points);
    }

    @Test
    public void calculate_POLE_VAULT_points () {
        final DecathlonService decathlonService = new DecathlonService();

        DecathlonResultDto decathlonResultDto = decathlonService.calculatePoints(eventResults);

        int points = decathlonResultDto
                .getEventResultDto()
                .stream()
                .filter(event -> event.getEventId()
                        .equals("POLE_VAULT"))
                .mapToInt(event -> event.getPoints())
                .sum();

        assertEquals(998, points);
    }

    @Test
    public void calculate_JAVELIN_THROW_points () {
        final DecathlonService decathlonService = new DecathlonService();

        DecathlonResultDto decathlonResultDto = decathlonService.calculatePoints(eventResults);

        int points = decathlonResultDto
                .getEventResultDto()
                .stream()
                .filter(event -> event.getEventId()
                        .equals("JAVELIN_THROW"))
                .mapToInt(event -> event.getPoints())
                .sum();

        assertEquals(1000, points);
    }

    @Test
    public void calculate_M1500_points () {
        final DecathlonService decathlonService = new DecathlonService();

        DecathlonResultDto decathlonResultDto = decathlonService.calculatePoints(eventResults);

        int points = decathlonResultDto
                .getEventResultDto()
                .stream()
                .filter(event -> event.getEventId()
                        .equals("M1500"))
                .mapToInt(event -> event.getPoints())
                .sum();

        assertEquals(1000, points);
    }


    @Test
    public void calculate_total_points () {
        final DecathlonService decathlonService = new DecathlonService();

        DecathlonResultDto decathlonResultDto = decathlonService.calculatePoints(eventResults);

        assertEquals(9990, decathlonResultDto.getPoints());
    }
}
