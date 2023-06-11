package com.example.diplomski.service;

import com.example.diplomski.enums.ActivityStatus;
import com.example.diplomski.model.FoodItem;
import com.example.diplomski.model.NutrientQuantity;
import com.example.diplomski.repository.FoodItemRepository;
import com.example.diplomski.repository.NutrientQuantityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Service
public class FoodService {
    @Autowired
    private FoodItemRepository foodItemRepository;
    @Autowired
    private NutrientQuantityRepository nutrientQuantityRepository;

//    FoodService() {
//        this.loadFood();
//    }

    public List<FoodItem> getFood(String search, int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return foodItemRepository.findByNameContaining(search, pageable);
    }

    public void loadFood() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/static/nutrition.csv"))) {
            String[] header = br.readLine().split(",");
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                FoodItem foodItem = new FoodItem();
                foodItem.setId(Long.parseLong(values[0]));
                if (line.contains("\"")) {
                    foodItem.setName(line.split("\"")[1]);
                    values = line.replaceAll("\".*?\"", "").split(",");
                } else {
                    foodItem.setName(values[1].replaceAll("^\"|\"$", ""));
                }

                HashMap<String, Double> nutrients = new HashMap<>();
                Set<NutrientQuantity> nutrientQuantityList = new HashSet<>();

                for (int h = 3; h < header.length; h++) {
                    double v;
                    try {
                        v = Double.parseDouble(values[h].replaceAll("[^\\d.]", ""));
                    } catch (Exception ignored) {
                        v = 0.0;
                    }
                    nutrients.put(header[h], v);

                    NutrientQuantity nutrientQuantity = new NutrientQuantity();
                    nutrientQuantity.setNutrient(header[h]);
                    nutrientQuantity.setQuantity(v);
                    nutrientQuantityRepository.save(nutrientQuantity);

                    nutrientQuantityList.add(nutrientQuantity);
                }

                foodItem.setNutrients(nutrientQuantityList);
                foodItem.setCalories(nutrients.get("calories"));
                foodItem.setCarbs(nutrients.get("carbohydrate"));
                foodItem.setFat(nutrients.get("total_fat"));
                foodItem.setProtein(nutrients.get("protein"));

                foodItemRepository.save(foodItem);
            }
            System.out.println("Food loaded.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FoodItem getFoodByName(String name) throws InstanceNotFoundException {
        return foodItemRepository.findByName(name).orElseThrow(() -> new InstanceNotFoundException("Food item not found."));
    }

    public FoodItem getFoodById(Long id) throws InstanceNotFoundException {
        return foodItemRepository.findById(id).orElseThrow(() -> new InstanceNotFoundException("Food item not found."));
    }

    public double calculateBMI(double weight, double height) {
        return weight / (height * height / 10000);
    }

    public ArrayList<ActivityStatus> getActivityStatuses() {
        return (ArrayList<ActivityStatus>) Arrays.stream(ActivityStatus.values()).toList();
    }
}
