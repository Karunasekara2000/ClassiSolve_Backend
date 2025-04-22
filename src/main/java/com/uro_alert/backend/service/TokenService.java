package com.uro_alert.backend.service;

import com.uro_alert.backend.model.Token;

import java.util.List;

public interface TokenService {
    List<Token> findAllValidTokenByUser(int id);

    Token findByToken(String token);
}
