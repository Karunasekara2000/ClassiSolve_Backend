package com.uro_alert.backend.repository;

import com.uro_alert.backend.model.Employee;
import com.uro_alert.backend.model.EmployeeDto;
import com.uro_alert.backend.model.mapper.EmployeeDtoMapper;
import com.uro_alert.backend.model.mapper.EmployeeMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public EmployeeRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Employee> findByTicketType(String ticketType) {

        StringBuilder query = new StringBuilder("SELECT * FROM employees WHERE expertise_type =:ticketType");
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("ticketType", ticketType);
        return namedParameterJdbcTemplate.query(query.toString(),param,new EmployeeMapper());
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        StringBuilder query = new StringBuilder("SELECT id, name FROM employees");
        return namedParameterJdbcTemplate.query(query.toString(), new EmployeeDtoMapper());
    }


}
