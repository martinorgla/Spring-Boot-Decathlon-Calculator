package com.industry62.decathlonrestservice;

public class EventResult {
    private Integer id;
    private String eventId;
    private Double result;
    private Integer points;

    public EventResult(Integer id, String eventId, Double result, Integer points) {
        this.id = id;
        this.eventId = eventId;
        this.result = result;
        this.points = points;
    }

    @Override
    public String toString() {
        return String.format(
                "EventResult[id=%d, eventId='%s', result='%f', points='%d']",
                id, eventId, result, points);
    }

    public long getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
