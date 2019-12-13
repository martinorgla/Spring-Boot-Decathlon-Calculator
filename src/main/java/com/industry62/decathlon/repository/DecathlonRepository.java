package com.industry62.decathlon.repository;

import com.industry62.decathlon.EventResult;
import com.industry62.decathlon.dto.DecathlonResultDto;
import com.industry62.decathlon.dto.EventResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Date;

@Repository
public class DecathlonRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*
     * Log decathlon result
     */
    public void storeDecathlonResult(DecathlonResultDto result) {
        String INSERT_RESULT_SQL = "INSERT INTO result (points, date) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Timestamp timestamp = new Timestamp(new Date().getTime());

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_RESULT_SQL, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, result.getPoints());
            ps.setTimestamp(2, timestamp);

            return ps;
        }, keyHolder);

        result.setId(keyHolder.getKey().longValue());

        for (EventResultDto eventResult : result.getEventResults()) {
            storeDecathlonEventResult(result.getId(), eventResult);
        }
    }

    /*
     * Log decathlon event result
     */
    private void storeDecathlonEventResult (Long result_id, EventResultDto result) {
        String INSERT_RESULT_SQL = "INSERT INTO event_result (result_id, eventId, result, points) VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(INSERT_RESULT_SQL, result_id, result.getEventId(), result.getResult(), result.getPoints());
    }

    private class EventResultRowMapper implements RowMapper<EventResult> {
        @Override
        public EventResult mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new EventResult(rs.getInt("id"), rs.getString("eventId"), rs.getDouble("result"), rs.getInt("points"));
        }
    }
}
