package com.example.diplomski.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@AllArgsConstructor
@NoArgsConstructor
public class FoodItem {
    private String id;
    private String name;
    private Double calories;
    private String servingSize;
    private HashMap<Nutrient, Double> nutrients;
}
