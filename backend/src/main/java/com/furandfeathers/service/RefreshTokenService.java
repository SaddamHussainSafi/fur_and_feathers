package com.furandfeathers.service;

import com.furandfeathers.exception.TokenRefreshException;
import com.furandfeathers.model.RefreshToken;
import com.furandfeathers.model.User;

import java.util.Optional;

public interface RefreshTokenService {
    Optional<RefreshToken> findByToken(String token);
    RefreshToken createRefreshToken(Long userId);
    RefreshToken verifyExpiration(RefreshToken token) throws TokenRefreshException;
    int deleteByUserId(Long userId);
    void delete(RefreshToken token);
}
