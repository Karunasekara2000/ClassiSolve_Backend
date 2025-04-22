package com.uro_alert.backend.repository;

import com.uro_alert.backend.model.User;
import com.uro_alert.backend.model.mapper.UserMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    @Override
    public User findById(int id) {
        StringBuilder query = new StringBuilder("select id,first_name,last_name,email,password,role from user where id = :id");
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return namedParameterJdbcTemplate.queryForObject(query.toString(), params, new UserMapper());
    }

    @Override
    public User findByEmail(String email) {

        StringBuilder query = new StringBuilder("select id,first_name,last_name,email,password,role from user where email = :email");
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("email", email);

        return namedParameterJdbcTemplate.queryForObject(query.toString(), params, new UserMapper());
    }

    @Override
    public User save(User user) {

        StringBuilder query = new StringBuilder("INSERT INTO user (first_name, last_name, email, password, role) VALUES (?, ?, ?, ?, ? )");

        KeyHolder key = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, String.valueOf(user.getRole()));
            return ps;
        },key);

        if(key.getKey() != null) {
            user.setId(key.getKey().intValue());
        }

        return user;
    }
}
