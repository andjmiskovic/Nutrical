package com.example.diplomski.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class BasicUserData {
    private String name;
    private String surname;
    private String email;
}
