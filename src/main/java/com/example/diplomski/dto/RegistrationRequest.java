package com.example.diplomski.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistrationRequest {
    @Email
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    @Size(max = 40, message = "First name cannot be longer than 40 characters.")
    private String firstName;

    @NotBlank
    @Size(max = 40, message = "Last name cannot be longer than 40 characters.")
    private String lastName;

    @NotBlank
    private String phoneNumber;

    private String licence;
}
