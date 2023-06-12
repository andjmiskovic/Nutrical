package com.example.diplomski.dto;

import com.example.diplomski.model.Nutrient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NutrientScore {
    private Nutrient nutrient;
    private Double goal;
    private Double amount;

    public void addAmount(double v) {
        this.amount += v;
    }
}
