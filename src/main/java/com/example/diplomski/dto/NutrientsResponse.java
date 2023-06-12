package com.example.diplomski.dto;

import com.example.diplomski.model.Nutrient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;

@Data
@Builder
@AllArgsConstructor
public class NutrientsResponse {
    private Double caloriesGoal;
    private Double calories;
    private HashMap<Nutrient, Double> nutrientsGoals;
    private HashMap<Nutrient, Double> nutrients;

    public NutrientsResponse() {
        this.calories = 0.0;
        this.nutrients = new HashMap<>();
        this.nutrientsGoals = new HashMap<>();
    }

    public void addCalories(double v) {
        this.calories += v;
    }
}
