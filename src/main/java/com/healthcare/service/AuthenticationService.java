package com.healthcare.service;

import com.healthcare.model.User;
import com.healthcare.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    @Transactional
    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return userRepository.findByEmail(authentication.getName())
                    .orElseThrow(() -> new RuntimeException("Couldn't get user!"));
        }
        throw new RuntimeException("Authenticated user not found");
    }
}
