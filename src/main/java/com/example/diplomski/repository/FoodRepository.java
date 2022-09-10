package com.example.diplomski.repository;

import com.example.diplomski.model.FoodItem;
import com.example.diplomski.model.Nutrient;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class FoodRepository {
    public static ArrayList<FoodItem> getFoodItems() {
        long startTime = System.nanoTime();
        ArrayList<FoodItem> food = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/static/nutrition.csv"))) {
            String[] header = br.readLine().split(",");
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                FoodItem foodItem = new FoodItem();
                foodItem.setId(values[0]);
                foodItem.setName(values[1].replaceAll("^\"|\"$", ""));

                HashMap<String, Double> nutrients = new HashMap<>();

                for (int h = 3; h < header.length; h++) {
                    double v;
                    try {
                        v = Double.parseDouble(values[h].replaceAll("[^\\d.]", ""));
                    } catch (Exception ignored) {
                        v = 0.0;
                    }
                    nutrients.put(header[h], v);
                }

                foodItem.setNutrients(nutrients);
                foodItem.setCalories(nutrients.get("calories"));
                foodItem.setCarbs(nutrients.get("carbohydrate"));
                foodItem.setFat(nutrients.get("total_fat"));
                foodItem.setProtein(nutrients.get("protein"));

                food.add(foodItem);
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
