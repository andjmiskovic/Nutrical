package com.example.diplomski.service;

import com.example.diplomski.enums.ActivityStatus;
import com.example.diplomski.model.FoodItem;
import com.example.diplomski.model.Nutrient;
import com.example.diplomski.model.User;
import com.example.diplomski.repository.FoodRepository;
import com.example.diplomski.repository.NutrientsRepository;
import org.springframework.stereotype.Service;

import java.util.*;
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

    public FoodItem getFoodByName(String name) {
        return this.food.get(name);
    }

    public double calculateBMI(String userName) {
        // TODO find user
        User user = new User();
        return user.getWeight() / (user.getHeight() * user.getHeight() / 10000);
    }


    public ArrayList<ActivityStatus> getActivityStatuses() {
        return (ArrayList<ActivityStatus>) Arrays.stream(ActivityStatus.values()).toList();
    }
}
