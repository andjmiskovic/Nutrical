package com.example.diplomski.model;

import com.example.diplomski.enums.ActivityStatus;
import com.example.diplomski.enums.HealthStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private Long password;
    private HealthStatus healthStatus;
    private ActivityStatus activityStatus;
//    private ? profilePhoto;
    private double weight;
    private double height;
    private Date dateOfBirth;
}
