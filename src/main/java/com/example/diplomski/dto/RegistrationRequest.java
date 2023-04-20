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
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\\\d)(?=.*[@$!%*#?&^])[A-Za-z\\\\d@$!%*#?&^]{12,}$\n",
            message = "Password must have at least 6 characters, at least one uppercase letter, " +
                    "one lowercase letter and one digit.")
    private String password;

    @NotBlank
    private String passwordConfirmation;

    @NotBlank
    @Size(max = 40, message = "First name cannot be longer than 40 characters.")
    private String firstName;

    @NotBlank
    @Size(max = 40, message = "Last name cannot be longer than 40 characters.")
    private String lastName;
}
