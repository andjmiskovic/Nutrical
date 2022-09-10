package com.example.diplomski.model;

import lombok.*;

import java.util.HashMap;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FoodItem {
    private String id;
    private String name;
    private Double calories;
    private Double protein;
    private Double fat;
    private Double carbs;
    private HashMap<String, Double> nutrients;
}
