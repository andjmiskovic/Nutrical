package com.example.diplomski.model;

import com.example.diplomski.enums.ActivityStatus;
import com.example.diplomski.enums.HealthStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientData {
    @Id
    private Long id;
    @Enumerated(EnumType.STRING)
    private HealthStatus healthStatus;
    @Enumerated(EnumType.STRING)
    private ActivityStatus activityStatus;
    @Column(name = "weight_kg")
    private double weight;
    @Column(name = "height_cm")
    private double height;
    @Temporal(TemporalType.DATE)
    @Column(name = "birth")
    private Date dateOfBirth;
}
