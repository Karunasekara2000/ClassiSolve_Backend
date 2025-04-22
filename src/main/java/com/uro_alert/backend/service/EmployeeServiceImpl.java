package com.uro_alert.backend.service;

import com.uro_alert.backend.model.Employee;
import com.uro_alert.backend.model.EmployeeDto;
import com.uro_alert.backend.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getEmployeesByTicketType(String ticketType) {
        return employeeRepository.findByTicketType(ticketType);
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        return employeeRepository.getAllEmployee();
    }
}
