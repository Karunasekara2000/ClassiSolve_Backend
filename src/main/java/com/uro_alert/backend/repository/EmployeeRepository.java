package com.uro_alert.backend.repository;

import com.uro_alert.backend.model.Employee;
import com.uro_alert.backend.model.EmployeeDto;

import java.util.List;

public interface EmployeeRepository {

    List<Employee> findByTicketType(String ticketType);

    public List<EmployeeDto> getAllEmployee();
}
