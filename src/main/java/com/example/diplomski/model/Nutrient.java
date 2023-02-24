package com.example.diplomski.model;

import com.example.diplomski.enums.NutrientKind;
import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Nutrient {
    @Id
    private String name;
    private String symbol;
    private String function;
    private String sources;
    private String unit;
    private NutrientKind kind;
    private Recommended recommended;
}
