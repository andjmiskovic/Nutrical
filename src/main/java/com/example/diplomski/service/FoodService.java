package com.example.diplomski.service;

import com.example.diplomski.model.FoodItem;
import com.example.diplomski.model.Nutrient;
import com.example.diplomski.repository.FoodRepository;
import com.example.diplomski.repository.NutrientsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FoodService {

    public ArrayList<FoodItem> getFood() {
        return FoodRepository.getFoodItems();
    }
}
