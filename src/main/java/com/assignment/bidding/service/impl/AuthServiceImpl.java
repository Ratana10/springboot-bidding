package com.assignment.bidding.service.impl;

import com.assignment.bidding.config.jwt.JwtService;
import com.assignment.bidding.dto.AuthRequest;
import com.assignment.bidding.dto.AuthResponse;
import com.assignment.bidding.dto.RegisterRequest;
import com.assignment.bidding.model.User;
import com.assignment.bidding.repository.UserRepository;
import com.assignment.bidding.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse authenticate(AuthRequest request) {
       authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String token = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("user not found");
        }
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        userRepository.save(user);
        String token = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }
}