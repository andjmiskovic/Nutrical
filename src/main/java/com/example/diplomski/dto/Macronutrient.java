package com.example.diplomski.dto;

import com.example.diplomski.util.ClientUtils;
import lombok.Data;

@Data
public class Macronutrient {
    private Double value = 0.0;
    private Double goal = 0.0;
    private Double percent = 0.0;

    public void add(double v) {
        this.value += v;
    }

    public void setGoal(int percent, double calorieGoal, int caloriesPerGram) {
        this.goal = ClientUtils.roundAvoid(calorieGoal * percent / 100 / caloriesPerGram, 1);
    }
}
