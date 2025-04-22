package com.uro_alert.backend.service;

import com.uro_alert.backend.model.Employee;
import com.uro_alert.backend.model.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    List<Employee> getEmployeesByTicketType(String ticketType);
    List<EmployeeDto> getAllEmployee();
}
