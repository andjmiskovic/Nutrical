package com.example.diplomski.dto;

import com.example.diplomski.model.Meal;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DailyPlanResponse {
    private Long id;
    private List<Meal> meals;
    private String notes;
    private String userEmail;
    private String training;
    private int daysInPlan;
}
