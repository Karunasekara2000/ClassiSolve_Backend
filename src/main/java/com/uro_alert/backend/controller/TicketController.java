package com.uro_alert.backend.controller;

import com.uro_alert.backend.model.AssignTicket;
import com.uro_alert.backend.model.AssignTicketDto;
import com.uro_alert.backend.model.Ticket;
import com.uro_alert.backend.model.TicketStatus;
import com.uro_alert.backend.service.TicketService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/tickets")
public class TicketController {


    private final RestTemplate restTemplate;
    private final TicketService ticketService;

    public TicketController(RestTemplate restTemplate, TicketService ticketService) {
        this.restTemplate = restTemplate;
        this.ticketService = ticketService;
    }

    @GetMapping("/search")
    public ResponseEntity<?> getPredictedTickets() {
        List<Ticket> tickets = ticketService.getTickets();


        // Convert Ticket objects to Map<String, Object> (for JSON)
        List<Map<String, Object>> ticketPayload = tickets.stream()
                .map(this::convertTicketToMap)
                .collect(Collectors.toList());

        String flaskUrl = "http://localhost:5000/predict";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<List<Map<String, Object>>> requestEntity = new HttpEntity<>(ticketPayload, headers);
        ResponseEntity<Object> response = restTemplate.postForEntity(flaskUrl, requestEntity, Object.class);


        List<Map<String, Object>> enriched = new ArrayList<>();
        List<Map<String, Object>> predictedResults = (List<Map<String, Object>>) response.getBody();


        for (int i = 0; i < predictedResults.size(); i++) {
            Map<String, Object> predicted = predictedResults.get(i);
            Ticket originalTicket = tickets.get(i);

            predicted.put("Customer Name", originalTicket.getCustomerName());
            predicted.put("Customer Email", originalTicket.getCustomerEmail());

            enriched.add(predicted);
        }

        return ResponseEntity.ok(enriched);


        //return ResponseEntity.ok(response.getBody());
    }

    private Map<String, Object> convertTicketToMap(Ticket ticket) {
        return Map.of(
                "Customer Age", ticket.getCustomerAge(),
                "Customer Gender", ticket.getCustomerGender(),
                "Customer Satisfaction Rating", ticket.getCustomerSatisfactionRating(),
                "Product Purchased", ticket.getProductPurchased(),
                "Ticket Channel", ticket.getTicketChannel(),
                "Ticket Description", ticket.getTicketDescription(),
                "Ticket Priority", ticket.getTicketPriority(),
                "Ticket Subject", ticket.getTicketSubject()
        );
    }

    @PostMapping("/assign")
    public AssignTicket assignTicket(@RequestBody AssignTicket assignTicket) {
        return ticketService.assignTicketToEmployee(assignTicket);
    }

    @GetMapping("/assigned-tickets/{id}")
    public ResponseEntity<List<AssignTicketDto>> getAssignedTickets(@PathVariable("id") int id) {
        return ResponseEntity.ok(ticketService.getAssignedTicketsByEmployeeId(id));
    }

    @PutMapping("/update-status/{ticketId}")
    public ResponseEntity<?> updateTicketStatus(@PathVariable int ticketId, @RequestBody Map<String, String> payload) {
        String newStatus = payload.get("status");
        ticketService.updateStatus(ticketId, newStatus);
        return ResponseEntity.ok("Status updated");
    }

    @GetMapping("/assign/tickets")
    public ResponseEntity<List<TicketStatus>> getAssignedTicketStatuses() {
        List<TicketStatus> assignedStatuses = ticketService.getTicketStatus();
        return ResponseEntity.ok(assignedStatuses);
    }
}
