package com.furandfeathers.repository;

import com.furandfeathers.model.RefreshToken;
import com.furandfeathers.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    
    Optional<RefreshToken> findByToken(String token);
    
    Optional<RefreshToken> findByUser(User user);
    
    @Modifying
    int deleteByUser(User user);
    
    @Modifying
    int deleteByUser_Id(Long userId);
}
