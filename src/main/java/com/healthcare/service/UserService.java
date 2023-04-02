package com.healthcare.service;

import com.healthcare.configuration.PasswordEncoder;
import com.healthcare.model.User;
import com.healthcare.model.enums.Role;
import com.healthcare.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationService authenticationService;

    public void save(User user) {
        try {
            user.setRole(Role.USER);
            user.setPassword(passwordEncoder.encoder().encode(user.getPassword()));
            userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Couldn't save user!\n" + e.getMessage());
        }
    }

    public User getUser() {
        return authenticationService.getUser();
    }

    @Transactional
    public void delete() {
        try {
            userRepository.delete(
                    authenticationService.getUser()
            );
        } catch (Exception e) {
            throw new RuntimeException("Couldn't delete user!\n" + e.getMessage());
        }
    }

    public void update(User userUpdate) {
        try {
            User user = authenticationService.getUser();
            user.setFirstname(userUpdate.getFirstname());
            user.setLastname(userUpdate.getLastname());
            user.setEmail(userUpdate.getEmail());
            user.setDateOfBirth(userUpdate.getDateOfBirth());
            userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Couldn't update user!\n" + e.getMessage());
        }
    }
}
