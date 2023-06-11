package com.example.diplomski.config;

import com.example.diplomski.service.FoodService;
import com.example.diplomski.service.NutrientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataProvider implements CommandLineRunner {
    @Autowired
    private FoodService foodService;
    @Autowired
    private NutrientsService nutrientsService;

    @Override
    public void run(String... args) throws Exception {
        nutrientsService.loadNutrients();
        foodService.loadFood();
    }
}