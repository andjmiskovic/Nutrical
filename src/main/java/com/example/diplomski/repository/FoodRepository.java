package com.example.diplomski.repository;

import com.example.diplomski.model.FoodItem;
import com.example.diplomski.model.NutrientQuantity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FoodRepository {
    public static HashMap<String, FoodItem> getFoodItems() {
        long startTime = System.nanoTime();
        HashMap<String, FoodItem> food = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/static/nutrition.csv"))) {
            String[] header = br.readLine().split(",");
            String line;
            while ((line = br.readLine()) != null) {
                FoodItem foodItem = new FoodItem();
                String[] values = line.split(",");
                foodItem.setId(values[0]);

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
                    nutrientQuantityList.add(nutrientQuantity);
                }

                foodItem.setNutrients(nutrientQuantityList);
                foodItem.setCalories(nutrients.get("calories"));
                foodItem.setCarbs(nutrients.get("carbohydrate"));
                foodItem.setFat(nutrients.get("total_fat"));
                foodItem.setProtein(nutrients.get("protein"));

                food.put(foodItem.getName(), foodItem);
            }
            long endTime = System.nanoTime();

            long duration = (endTime - startTime) / 1000;
            System.out.println(duration);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return food;
    }
}
