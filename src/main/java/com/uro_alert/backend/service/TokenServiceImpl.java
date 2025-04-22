package com.uro_alert.backend.service;

import com.uro_alert.backend.model.Token;
import com.uro_alert.backend.repository.TokenRepository;

import java.util.List;

public class TokenServiceImpl implements TokenService{
    private final TokenRepository tokenRepository;

    public TokenServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }


    @Override
    public List<Token> findAllValidTokenByUser(int id) {
        return tokenRepository.findAllValidTokenByUser(id);
    }

    @Override
    public Token findByToken(String token) {
        return tokenRepository.findByToken(token);
    }
}
