package com.industry62.decathlon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EventResultDto {
    private Integer id;
    private String eventId;
    private Double result;
    private Integer points;

    public EventResultDto(Integer id, String eventId, Double result, Integer points) {
        this.id = id;
        this.eventId = eventId;
        this.result = result;
        this.points = points;
    }

    public EventResultDto(@JsonProperty("eventId") String eventId, @JsonProperty("result") double result) {
        this.eventId = eventId;
        this.result = result;
    }

    @Override
    public String toString() {
        return String.format(
                "EventResult[id=%d, eventId='%s', result='%f', points='%d']",
                id, eventId, result, points);
    }

    public Integer getId() {
        return id;
    }

    public String getEventId() {
        return eventId;
    }

    public Double getResult() {
        return result;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
