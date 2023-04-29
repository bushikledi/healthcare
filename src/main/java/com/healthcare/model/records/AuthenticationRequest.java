package com.healthcare.model.records;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record AuthenticationRequest(
        @Email
        String email,
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")
        String password) {
}
