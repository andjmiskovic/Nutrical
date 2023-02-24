package com.example.diplomski.service;

import com.example.diplomski.enums.ActivityStatus;
import com.example.diplomski.exceptions.FoodItemNotFoundException;
import com.example.diplomski.model.FoodItem;
import com.example.diplomski.repository.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FoodService {

    public HashMap<String, FoodItem> food;

    FoodService() {
        this.loadFood();
    }

    public HashMap<String, FoodItem> getFood() {
        return this.food;
    }

    public ArrayList<FoodItem> getFood(String search, int limit) {
        ArrayList<FoodItem> foodItems = new ArrayList<>();
        for (Map.Entry<String, FoodItem> e : this.food.entrySet()) {
            if (e.getKey().toLowerCase().startsWith(search.toLowerCase())) {
                foodItems.add(e.getValue());
            }
        }
        return (ArrayList<FoodItem>) foodItems.stream().limit(limit).collect(Collectors.toList());
    }

    public void loadFood() {
        this.food = FoodRepository.getFoodItems();
    }

    public FoodItem getFoodByName(String name) throws FoodItemNotFoundException {
        if (this.food.containsKey(name))
            return this.food.get(name);
        throw new FoodItemNotFoundException("Food item not found.");
    }

    public double calculateBMI(double weight, double height) {
        return weight / (height * height / 10000);
    }

    public ArrayList<ActivityStatus> getActivityStatuses() {
        return (ArrayList<ActivityStatus>) Arrays.stream(ActivityStatus.values()).toList();
    }
}
