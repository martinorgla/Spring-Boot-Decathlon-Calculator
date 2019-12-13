package com.industry62.decathlon;

import java.sql.Timestamp;

public class Result {
    private Integer id;
    private Integer points;
    private Timestamp timestamp;

    public Result(Integer id, Integer points, Timestamp timestamp) {
        this.id = id;
        this.points = points;
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
