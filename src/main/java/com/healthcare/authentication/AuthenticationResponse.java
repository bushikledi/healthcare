package com.healthcare.authentication;

public record AuthenticationResponse(String accessToken,
                                     String refreshToken) {
}
