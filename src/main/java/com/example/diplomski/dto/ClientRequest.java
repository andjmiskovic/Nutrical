package com.example.diplomski.dto;

import com.example.diplomski.enums.ActivityStatus;
import com.example.diplomski.enums.HealthStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
public class ClientRequest {
    private String name;
    private String surname;
    private String email;
    private HealthStatus healthStatus;
    private ActivityStatus activityStatus;
    private double weight;
    private double height;
    private Date dateOfBirth;
    private String nutritionistEmail;
}
