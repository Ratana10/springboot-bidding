package com.assignment.bidding.dto;

import com.assignment.bidding.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.Registered;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
public class UserDto {
    private Long id;

    @NotNull(message = "username is required")
    private String username;

    @NotNull(message = "email is required")
    private String email;

    @NotNull(message = "password is required")
    private String password;

    @NotNull(message = "role is required")
    private Role role;

}
