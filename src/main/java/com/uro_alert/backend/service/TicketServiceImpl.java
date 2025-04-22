package com.uro_alert.backend.service;

import com.uro_alert.backend.model.AssignTicket;
import com.uro_alert.backend.model.AssignTicketDto;
import com.uro_alert.backend.model.Ticket;
import com.uro_alert.backend.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Ticket> getTickets() {
        return ticketRepository.getAllTickets();
    }

    @Override
    public AssignTicket assignTicketToEmployee(AssignTicket assignTicket) {
        return ticketRepository.saveAssignment(assignTicket);
    }

    @Override
    public List<AssignTicketDto> getAssignedTicketsByEmployeeId(int employeeId) {
        return ticketRepository.getAssignedTicketsByEmployeeId(employeeId);
    }
}
