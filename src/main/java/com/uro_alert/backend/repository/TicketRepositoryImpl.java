package com.uro_alert.backend.repository;

import com.uro_alert.backend.model.AssignTicket;
import com.uro_alert.backend.model.AssignTicketDto;
import com.uro_alert.backend.model.Ticket;
import com.uro_alert.backend.model.TicketStatus;
import com.uro_alert.backend.model.mapper.AssignTicketDtoMapper;
import com.uro_alert.backend.model.mapper.TicketMapper;
import com.uro_alert.backend.model.mapper.TicketStatusMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class TicketRepositoryImpl implements TicketRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public TicketRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Ticket> getAllTickets() {

        StringBuilder query = new StringBuilder("SELECT * FROM support_tickets");
        return namedParameterJdbcTemplate.query(query.toString(), new TicketMapper());
    }

    @Override
    public AssignTicket saveAssignment(AssignTicket assignTicket) {


        StringBuilder query = new StringBuilder("INSERT INTO ticket_assignments (ticket_id, employee_id, ticket_subject, predicted_time, customer_name, customer_email, assigned_at) VALUES (?, ?, ?, ?, ?, ?, ?)");

        KeyHolder key = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, assignTicket.getTicketId());
            ps.setInt(2, assignTicket.getEmployeeId());
            ps.setString(3, assignTicket.getTicketSubject());
            ps.setString(4, assignTicket.getResolutionTime());
            ps.setString(5, assignTicket.getCustomerName());
            ps.setString(6, assignTicket.getCustomerEmail());
            ps.setTimestamp(7, Timestamp.valueOf(assignTicket.getAssignedAt()));
            return ps;
        }, key);

        if (key.getKey() != null) {
            assignTicket.setId(key.getKey().intValue());
        }

        return assignTicket;
    }

    @Override
    public List<AssignTicketDto> getAssignedTicketsByEmployeeId(int employeeId) {
        StringBuilder query = new StringBuilder("SELECT ticket_id, ticket_subject, predicted_time, customer_name, customer_email, assigned_at, status FROM ticket_assignments WHERE employee_id = :employeeId");

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("employeeId", employeeId);

        return namedParameterJdbcTemplate.query(query.toString(), params, new AssignTicketDtoMapper());
    }

    @Override
    public void updateStatus(int ticketId, String status) {
        String sql = "UPDATE ticket_assignments SET status = ? WHERE ticket_id = ?";
        jdbcTemplate.update(sql, status, ticketId);
    }

    @Override
    public List<TicketStatus> getTicketStatus() {
        StringBuilder query = new StringBuilder("SELECT ticket_id, status FROM ticket_assignments");
        return namedParameterJdbcTemplate.query(query.toString(), new TicketStatusMapper());
    }
}
