package com.example.diplomski.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Plan {
    @Id
    private Long id;
    @OneToOne
    private Client client;
    @ManyToOne
    private Nutritionist nutritionist;
    @OneToMany
    private List<DailyPlan> dailyPlans;
}
