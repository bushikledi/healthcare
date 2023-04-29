package com.healthcare.authentication;

import com.healthcare.configuration.PasswordEncoder;
import com.healthcare.jwt.JwtService;
import com.healthcare.model.Token;
import com.healthcare.model.User;
import com.healthcare.model.enums.Role;
import com.healthcare.model.records.AuthenticationRequest;
import com.healthcare.model.records.AuthenticationResponse;
import com.healthcare.model.records.RefreshTokenResponse;
import com.healthcare.model.records.UserRequest;
import com.healthcare.repository.TokenRepository;
import com.healthcare.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JwtAuthService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(UserRequest request) {
        User user = User.builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .gender(request.gender())
                .dateOfBirth(request.dateOfBirth())
                .password(passwordEncoder.encoder().encode(request.password()))
                .role(Role.USER)
                .build();
        User savedUser = userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser.getUserId(), jwtToken);
        return new AuthenticationResponse(jwtToken, refreshToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        User user = userRepository.findByEmail(request.email())
                .orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        revokeUserTokens(user);
        saveUserToken(user.getUserId(), jwtToken);
        return new AuthenticationResponse(jwtToken, refreshToken);
    }

    private void saveUserToken(Integer userId, String jwtToken) {
        tokenRepository.save(
                Token.builder()
                        .userId(userId)
                        .token(jwtToken)
                        .revoked(false)
                        .build()
        );
    }

    private void revokeUserTokens(User user) {
        List<Token> validUserTokens = tokenRepository.findTokensByUserIdAndRevoked(user.getUserId(), false)
                .orElseThrow(() -> new RuntimeException("Couldn't revoke user tokens."));
        validUserTokens.forEach(
                token -> token.setRevoked(true)
        );
        tokenRepository.saveAll(validUserTokens);
    }

    public RefreshTokenResponse refreshToken(
            HttpServletRequest request
    ) {
        final String authHeader = request.getHeader("refresh-token");
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractEmail(refreshToken);
        if (userEmail != null) {
            User user = this.userRepository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                String accessToken = jwtService.generateToken(user);
                revokeUserTokens(user);
                saveUserToken(user.getUserId(), accessToken);
                return new RefreshTokenResponse(accessToken);
            }
        }
        throw new RuntimeException("Couldn't refresh token!");
    }
}
