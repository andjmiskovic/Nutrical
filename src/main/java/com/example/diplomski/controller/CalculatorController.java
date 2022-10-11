package com.example.diplomski.controller;

import com.example.diplomski.enums.ActivityStatus;
import com.example.diplomski.model.FoodItem;
import com.example.diplomski.model.Nutrient;
import com.example.diplomski.service.FoodService;
import com.example.diplomski.service.NutrientsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {

    private final NutrientsService nutrientsService;
    private final FoodService foodService;

    public CalculatorController(NutrientsService nutrientsService, FoodService foodService) {
        this.nutrientsService = nutrientsService;
        this.foodService = foodService;
    }

    @GetMapping("/get-nutrients")
    public ArrayList<Nutrient> getNutrients() {
        return nutrientsService.getNutrients();
    }

    @GetMapping("/get-food")
    public ArrayList<FoodItem> getFood(String search, int limit) {
        return foodService.getFood(search, limit);
    }

    @GetMapping("/get-food-by-name")
    public FoodItem getFoodByName(String name) {
        return foodService.getFoodByName(name);
    }

    @GetMapping("/calculate-bmi")
    public double calculateBMI(String userName) {
        return foodService.calculateBMI(userName);
    }

    @GetMapping("/get-activity-statuses")
    public ArrayList<ActivityStatus> getActivityStatuses() {
        return foodService.getActivityStatuses();
    }
}
