package com.example.diplomski.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginVerificationRequest {

    @NotBlank
    private String password;
}
