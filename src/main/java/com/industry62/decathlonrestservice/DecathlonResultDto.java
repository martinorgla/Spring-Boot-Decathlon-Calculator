package com.industry62.decathlonrestservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"points", "eventResults"})
public class DecathlonResultDto {
    private Integer points;
    private List<EventResultDto> eventResults;

    public DecathlonResultDto(@JsonProperty("eventResultDto") List<EventResultDto> eventResultDto) {
        this.eventResults = eventResultDto;
        this.points = this.eventResults.stream().mapToInt(result -> result.getPoints()).sum();
    }

    public List<EventResultDto> getEventResultDto() {
        return eventResults;
    }

    public Integer getPoints() {
        return points;
    }
}
