package com.uro_alert.backend.model.mapper;

import com.uro_alert.backend.model.Ticket;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketMapper implements RowMapper<Ticket> {
    @Override
    public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Ticket.builder()
                .id(rs.getInt("id"))
                .customerAge(rs.getInt("customer_age"))
                .customerName(rs.getString("customer_name"))
                .customerEmail(rs.getString("customer_email"))
                .customerGender(rs.getString("customer_gender"))
                .customerSatisfactionRating(rs.getDouble("customer_satisfaction_rating"))
                .productPurchased(rs.getString("product_purchased"))
                .ticketChannel(rs.getString("ticket_channel"))
                .ticketDescription(rs.getString("ticket_description"))
                .ticketPriority(rs.getString("ticket_priority"))
                .ticketSubject(rs.getString("ticket_subject"))
                .build();
    }
}
