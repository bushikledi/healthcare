package com.healthcare.model.records;

public record AuthenticationResponse(String accessToken,
                                     String refreshToken) {
}
