package com.example.diplomski.controller;

import com.example.diplomski.enums.ActivityStatus;
import com.example.diplomski.model.FoodItem;
import com.example.diplomski.model.Nutrient;
import com.example.diplomski.service.FoodService;
import com.example.diplomski.service.NutrientsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.InstanceNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@AllArgsConstructor
@RequestMapping("/api/calculator")
public class CalculatorController {

    private final NutrientsService nutrientsService;
    private final FoodService foodService;

    @GetMapping("/get-nutrients/{planId}")
    public HashMap<Nutrient, Double> getNutrients(@PathVariable Long planId) {
        return nutrientsService.getNutrients(planId);
    }

    @GetMapping("/get-nutrients")
    public ArrayList<Nutrient> getNutrientsList() {
        return nutrientsService.getNutrients();
    }

    @GetMapping("/get-food")
    public ArrayList<FoodItem> getFood(String search, int limit) {
        return foodService.getFood(search, limit);
    }

    @GetMapping("/get-food-by-name")
    public FoodItem getFoodByName(String name) throws InstanceNotFoundException {
        return foodService.getFoodByName(name);
    }

    @GetMapping("/get-activity-statuses")
    public ArrayList<ActivityStatus> getActivityStatuses() {
        return foodService.getActivityStatuses();
    }
}
