package com.example.diplomski.dto;

import com.example.diplomski.enums.ActivityStatus;
import com.example.diplomski.enums.HealthStatus;
import lombok.Data;

import java.util.Date;

@Data
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
    private double calorieGoal;
    private int proteinPercent;
    private int carbsPercent;
    private int fatPercent;
}
