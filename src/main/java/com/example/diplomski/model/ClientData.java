package com.example.diplomski.model;

import com.example.diplomski.enums.ActivityStatus;
import com.example.diplomski.enums.HealthStatus;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientData {
    private HealthStatus healthStatus;
    private ActivityStatus activityStatus;
    private double weight;
    private double height;
    private Date dateOfBirth;
}
