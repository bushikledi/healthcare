package com.healthcare.service;

import com.healthcare.model.records.UserRequest;
import com.healthcare.configuration.PasswordEncoder;
import com.healthcare.model.User;
import com.healthcare.model.records.UserResponse;
import com.healthcare.repository.TokenRepository;
import com.healthcare.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;

    public UserResponse getUser() {
        User user = authenticationService.getUser();
        return UserResponse.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .dateOfBirth(user.getDateOfBirth())
                .gender(user.getGender())
                .email(user.getEmail())
                .password("")
                .role(user.getRole())
                .build();
    }

    @Transactional
    public void delete() {
        try {
            User user = authenticationService.getUser();
            tokenRepository.deleteAllByUserId(user.getUserId());
            userRepository.delete(user);
        } catch (Exception e) {
            throw new RuntimeException("Couldn't delete user!\n" + e.getMessage());
        }
    }

    public void update(UserRequest userRequest) {
        try {
            User user = authenticationService.getUser();
            if (!userRequest.firstname().isEmpty())
                user.setFirstname(userRequest.firstname());
            if (!userRequest.lastname().isEmpty())
                user.setLastname(userRequest.lastname());
            if (!userRequest.dateOfBirth().toString().isEmpty())
                user.setDateOfBirth(userRequest.dateOfBirth());
            if (!userRequest.password().isEmpty()) {
                if (userRequest.password().matches("^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")) {
                    user.setPassword(passwordEncoder.encoder().encode(userRequest.password()));
                } else throw new IllegalArgumentException("Password must be at least 8 characters " +
                        "long and contain both letters and numbers.");
            }
            if (!userRequest.gender().toString().isEmpty())
                user.setGender(userRequest.gender());
            userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Couldn't update user!\n" + e.getMessage());
        }
    }
}
