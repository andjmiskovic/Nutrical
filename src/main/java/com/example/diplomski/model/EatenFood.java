package com.example.diplomski.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EatenFood {
    private FoodItem food;
    private Double amount;
    private HashMap<Nutrient, Double> nutrients;
}
