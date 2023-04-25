package com.example.diplomski.service;

import com.example.diplomski.model.*;
import com.example.diplomski.repository.DairyRepository;
import com.example.diplomski.repository.NutrientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class NutrientsService {

    public ArrayList<Nutrient> nutrients;

    @Autowired
    private DairyRepository dairyRepository;

    NutrientsService() {
        this.nutrients = NutrientsRepository.getNutrients();
    }

    public ArrayList<Nutrient> getNutrients() {
        return this.nutrients;
    }

    public HashMap<Nutrient, Double> createEmptyNutrientMap() {
        HashMap<Nutrient, Double> hashMap = new HashMap<>();
        for (Nutrient nutrient : nutrients) {
            hashMap.put(nutrient, 0.0);
        }
        return hashMap;
    }

    public HashMap<Nutrient, Double> getNutrients(Long planId) {
        HashMap<Nutrient, Double> emptyNutrients = createEmptyNutrientMap();
        if (dairyRepository.findById(planId).isPresent()) {
            DailyPlan dailyPlan = dairyRepository.findById(planId).get();
            for (Tag tag : dailyPlan.getTags()) {
                for (EatenFood food : tag.getEatenFood()) {
                    addFoodNutrients(emptyNutrients, food.getFoodItem(), food.getQuantity());
                }
            }
        }
        return emptyNutrients;
    }

    private void addFoodNutrients(HashMap<Nutrient, Double> nutrients, FoodItem foodItem, Double amount) {
        nutrients.replaceAll((nutrient, value) -> nutrients.get(nutrient) + getNutrientQuantity(foodItem, nutrient) * amount / 100);
    }

    private Double getNutrientQuantity(FoodItem foodItem, Nutrient nutrient) {
        for (NutrientQuantity nutrientQuantity : foodItem.getNutrients()) {
            if (nutrientQuantity.getNutrient().equals(nutrient.getName())) {
                return nutrientQuantity.getQuantity();
            }
        }
        throw new NotFoundException("Food item not found.");
    }
}
