package com.uro_alert.backend.service;

import com.uro_alert.backend.model.User;

public interface UserService {
    User findByEmail(String email);
}
