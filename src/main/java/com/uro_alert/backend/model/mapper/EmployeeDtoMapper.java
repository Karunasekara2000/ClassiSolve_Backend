package com.uro_alert.backend.model.mapper;

import com.uro_alert.backend.model.Employee;
import com.uro_alert.backend.model.EmployeeDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDtoMapper implements RowMapper<EmployeeDto> {
    @Override
    public EmployeeDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return EmployeeDto.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .build();
    }
}
