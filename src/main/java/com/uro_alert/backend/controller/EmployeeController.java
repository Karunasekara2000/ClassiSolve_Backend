package com.uro_alert.backend.controller;

import com.uro_alert.backend.model.AssignTicket;
import com.uro_alert.backend.model.Employee;
import com.uro_alert.backend.model.EmployeeDto;
import com.uro_alert.backend.model.Ticket;
import com.uro_alert.backend.service.EmployeeService;
import com.uro_alert.backend.service.TicketService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/employees")
public class EmployeeController {


    private final EmployeeService employeeService;
    private final TicketService ticketService;

    public EmployeeController(EmployeeService employeeService, TicketService ticketService) {
        this.employeeService = employeeService;
        this.ticketService = ticketService;
    }


    @GetMapping("/assignees/by-type/{ticketType}")
    public List<Employee> getAssigneesByTicketType(@PathVariable String ticketType) {
        List<Employee> all = employeeService.getEmployeesByTicketType(ticketType);
        Collections.shuffle(all);
        return all.size() > 5 ? all.subList(0, 5) : all;
    }

    @PostMapping("/assign")
    public AssignTicket assignTicket(@RequestBody AssignTicket assignTicket) {
        assignTicket.setAssignedAt(LocalDateTime.now());
        return ticketService.assignTicketToEmployee(assignTicket);
    }

    @GetMapping("/list")
    public ResponseEntity<List<EmployeeDto>> getAllEmployeeNamesAndIds() {
        return ResponseEntity.ok(employeeService.getAllEmployee());
    }


}
