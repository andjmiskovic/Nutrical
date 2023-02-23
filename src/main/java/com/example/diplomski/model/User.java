package com.example.diplomski.model;

import com.example.diplomski.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String email;
    private String name;
    private String surname;
    private Long password;
    private Role role;
}
