package com.uro_alert.backend.repository;

import com.uro_alert.backend.model.User;

public interface UserRepository{

    User findById(int id);

    User findByEmail(String email);

    User save(User user);
}
