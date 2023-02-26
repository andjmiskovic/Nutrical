package com.example.diplomski.service;

import com.example.diplomski.model.DailyPlan;
import com.example.diplomski.model.FoodItem;
import com.example.diplomski.model.Nutrient;
import com.example.diplomski.model.Tag;
import com.example.diplomski.repository.DairyRepository;
import com.example.diplomski.repository.NutrientsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@AllArgsConstructor
@Service
public class NutrientsService {

    public ArrayList<Nutrient> nutrients;

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
                for (var food : tag.getEatenFood().keySet()) {
                    addFoodNutrients(emptyNutrients, food, tag.getEatenFood().get(food));
                }
            }
        }
        return emptyNutrients;
    }

    private void addFoodNutrients(HashMap<Nutrient, Double> nutrients, FoodItem foodItem, Double amount) {
        nutrients.replaceAll((n, v) -> nutrients.get(n) + foodItem.getNutrients().get(n.getName()) * amount / 100);
    }
}
