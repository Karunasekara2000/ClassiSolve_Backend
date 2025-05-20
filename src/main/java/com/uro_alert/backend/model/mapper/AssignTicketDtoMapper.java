package com.uro_alert.backend.model.mapper;

import com.uro_alert.backend.model.AssignTicketDto;
import com.uro_alert.backend.model.EmployeeDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AssignTicketDtoMapper implements RowMapper<AssignTicketDto> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Override
    public AssignTicketDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return AssignTicketDto.builder()
                .ticketId(rs.getInt("ticket_id"))
                .ticketSubject(rs.getString("ticket_subject"))
                .resolutionTime(rs.getString("predicted_time"))
                .customerName(rs.getString("customer_name"))
                .customerEmail(rs.getString("customer_email"))
                .assignedAt(LocalDateTime.parse(rs.getString("assigned_at"), formatter))
                .status(rs.getString("status"))
                .build();
    }
}
