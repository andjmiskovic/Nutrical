package com.example.diplomski.controller;

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

    @GetMapping("/getNutrients")
    public ArrayList<Nutrient> getNutrients() {
        return nutrientsService.getNutrients();
    }

    @GetMapping("/getFood")
    public ArrayList<FoodItem> getFood() {
        return foodService.getFood();
    }
}
