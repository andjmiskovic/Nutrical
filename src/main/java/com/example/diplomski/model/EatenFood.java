package com.example.diplomski.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@NoArgsConstructor
@AllArgsConstructor
public class EatenFood {
    private FoodItem food;
    private Double amount;
    private HashMap<Nutrient, Double> nutrients;
}
