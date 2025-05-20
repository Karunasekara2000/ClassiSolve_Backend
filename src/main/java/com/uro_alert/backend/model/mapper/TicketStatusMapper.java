package com.uro_alert.backend.model.mapper;

import com.uro_alert.backend.model.AssignTicketDto;
import com.uro_alert.backend.model.TicketStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TicketStatusMapper implements RowMapper<TicketStatus> {


    @Override
    public TicketStatus mapRow(ResultSet rs, int rowNum) throws SQLException {
        return TicketStatus.builder()
                .ticketId(rs.getInt("ticket_id"))
                .status(rs.getString("status"))
                .build();
    }
}
