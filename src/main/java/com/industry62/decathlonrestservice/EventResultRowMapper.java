package com.industry62.decathlonrestservice;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventResultRowMapper implements RowMapper<EventResult> {
    @Override
    public EventResult mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new EventResult(rs.getInt("id"), rs.getString("eventId"), rs.getDouble("result"), rs.getInt("points"));
    }
}
