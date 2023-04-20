package com.example.diplomski.dto;

import com.example.diplomski.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String accessToken;
    private Long expiresAt;
    private Role role;
}
