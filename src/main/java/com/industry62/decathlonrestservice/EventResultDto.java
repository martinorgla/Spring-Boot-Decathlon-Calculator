package com.industry62.decathlonrestservice;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EventResultDto {
    private String eventId;
    private Double result;
    private Integer points;

    public EventResultDto(@JsonProperty("eventId") String eventId, @JsonProperty("result") double result) {
        this.eventId = eventId;
        this.result = result;
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
