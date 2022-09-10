package com.example.diplomski.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Nutrient {
    private String name;
    private String symbol;
    private String function;
    private String sources;
    private String unit;
    private NutrientKind kind;
    private Recommended recommended;
}
