package com.healthcare.model.records;

import com.healthcare.model.enums.Gender;
import com.healthcare.model.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UserResponse(
        @NotNull
        String firstname,
        @NotNull
        String lastname,
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")
        String password,
        @Email
        @NotNull
        @Column(unique = true)
        String email,
        @Enumerated(EnumType.STRING)
        Gender gender,
        @Enumerated(EnumType.STRING)
        Role role,
        @NotNull
        LocalDate dateOfBirth
) {
}
