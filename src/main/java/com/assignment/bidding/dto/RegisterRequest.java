package com.assignment.bidding.dto;

import com.assignment.bidding.enums.Role;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
public class RegisterRequest {
    @NotNull(message = "username is required")
    private String username;
    @NotNull(message = "email is required")
    private String email;
    @NotNull(message = "password is required")
    private String password;
    @NotNull(message = "role is required")
    private Role role;
}