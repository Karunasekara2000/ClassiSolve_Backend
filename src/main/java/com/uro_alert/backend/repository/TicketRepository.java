package com.uro_alert.backend.repository;

import com.uro_alert.backend.model.AssignTicket;
import com.uro_alert.backend.model.AssignTicketDto;
import com.uro_alert.backend.model.Ticket;

import java.util.List;

public interface TicketRepository {

    List<Ticket> getAllTickets();

    AssignTicket saveAssignment(AssignTicket assignTicket);

    List<AssignTicketDto> getAssignedTicketsByEmployeeId(int employeeId);
}
