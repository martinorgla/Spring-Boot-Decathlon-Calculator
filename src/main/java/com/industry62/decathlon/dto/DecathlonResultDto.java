package com.industry62.decathlon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.industry62.decathlon.dto.EventResultDto;

import java.util.List;

//@JsonPropertyOrder({"points", "eventResults"})
public class DecathlonResultDto {
    private Long id;
    private Integer points;
    private List<EventResultDto> eventResults;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public List<EventResultDto> getEventResults() {
        return eventResults;
    }

    public void setEventResults(List<EventResultDto> eventResults) {
        this.eventResults = eventResults;
    }
}
