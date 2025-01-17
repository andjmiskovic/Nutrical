package com.example.diplomski.dto;

import com.example.diplomski.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class UserInfoResponse {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;

    public UserInfoResponse(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
    }
}
