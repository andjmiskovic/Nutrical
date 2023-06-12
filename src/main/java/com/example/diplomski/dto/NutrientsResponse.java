package com.example.diplomski.dto;

import com.example.diplomski.enums.HealthStatus;
import com.example.diplomski.model.Nutrient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class NutrientsResponse {
    private Double caloriesGoal;
    private Double calories;
    private List<NutrientScore> nutrientsScore;

    public NutrientsResponse(List<Nutrient> nutrients, HealthStatus healthStatus) {
        this.calories = 0.0;
        this.nutrientsScore = new ArrayList<>();
        for (Nutrient n : nutrients) {
            this.nutrientsScore.add(new NutrientScore(n, n.getRecommended().getValue(healthStatus), 0.0));
        }
    }

    public void addCalories(double v) {
        this.calories += v;
    }
}
