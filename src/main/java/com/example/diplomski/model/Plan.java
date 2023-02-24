package com.example.diplomski.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Plan {
    @Id
    private Long id;
    private Client client;
    @OneToMany
    private Nutritionist nutritionist;
    private List<DailyPlan> dailyPlans;
}
