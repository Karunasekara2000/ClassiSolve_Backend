package com.uro_alert.backend.service;

import com.uro_alert.backend.model.AssignTicket;
import com.uro_alert.backend.model.AssignTicketDto;
import com.uro_alert.backend.model.Ticket;

import java.util.List;

public interface TicketService {

    List<Ticket> getTickets();
    AssignTicket assignTicketToEmployee(AssignTicket assignTicket);
    List<AssignTicketDto> getAssignedTicketsByEmployeeId(int employeeId);
}
