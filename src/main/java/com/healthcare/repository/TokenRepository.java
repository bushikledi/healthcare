package com.healthcare.repository;

import com.healthcare.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {
    Optional<Token> findByToken(String jwt);

    Optional<List<Token>> findTokensByUserIdAndRevoked(Integer userId, boolean revoked);

    void deleteAllByUserId(Integer userId);
}


