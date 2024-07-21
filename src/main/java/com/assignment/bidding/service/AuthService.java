package com.assignment.bidding.service;

import com.assignment.bidding.dto.AuthRequest;
import com.assignment.bidding.dto.AuthResponse;
import com.assignment.bidding.dto.RegisterRequest;

public interface AuthService {
    AuthResponse authenticate(AuthRequest request);
    AuthResponse register(RegisterRequest request);
}