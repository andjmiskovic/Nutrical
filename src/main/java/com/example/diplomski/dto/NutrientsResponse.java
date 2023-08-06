package com.example.diplomski.dto;

import com.example.diplomski.model.ClientData;
import com.example.diplomski.model.FoodItem;
import com.example.diplomski.model.Nutrient;
import com.example.diplomski.util.ClientUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class NutrientsResponse {
    private Double caloriesGoal;
    private Double calories;
    private Macronutrient protein;
    private Macronutrient carbs;
    private Macronutrient fat;
    private List<NutrientScore> nutrientsScore;

    private final int FAT_CALORIES = 9;
    private final int CARBS_CALORIES = 4;
    private final int PROTEIN_CALORIES = 4;

    public NutrientsResponse(List<Nutrient> nutrients, ClientData clientData) {
        this.calories = 0.0;
        this.carbs = new Macronutrient();
        this.fat = new Macronutrient();
        this.protein = new Macronutrient();
        this.nutrientsScore = new ArrayList<>();
        for (Nutrient n : nutrients) {
            this.nutrientsScore.add(new NutrientScore(n, n.getRecommended().getValue(clientData), 0.0));
        }
    }

    public void addMacronutrients(FoodItem item, double quantity) {
        this.addCalories(ClientUtils.roundAvoid(item.getCalories() * quantity / 100, 1));
        this.addProtein(ClientUtils.roundAvoid(item.getProtein() * quantity / 100, 1));
        this.addCarbs(ClientUtils.roundAvoid(item.getCarbs() * quantity / 100, 1));
        this.addFat(ClientUtils.roundAvoid(item.getFat() * quantity / 100, 1));
    }

    public void setGoals(ClientData clientData) {
        this.setCaloriesGoal(clientData.getCalorieGoal());
        this.setProteinGoal(clientData.getProteinPercent());
        this.setFatGoal(clientData.getFatPercent());
        this.setCarbsGoal(clientData.getCarbsPercent());
    }

    public void addCalories(double v) {
        this.calories += v;
    }

    public void addProtein(double v) {
        this.protein.add(v);
    }

    public void addCarbs(double v) {
        this.carbs.add(v);
    }

    public void addFat(double v) {
        this.fat.add(v);
    }

    public void setProteinGoal(int percent) {
        this.protein.setGoal(percent, this.caloriesGoal, PROTEIN_CALORIES);
    }

    public void setFatGoal(int percent) {
        this.fat.setGoal(percent, this.caloriesGoal, FAT_CALORIES);
    }

    public void setCarbsGoal(int percent) {
        this.carbs.setGoal(percent, this.caloriesGoal, CARBS_CALORIES);
    }

    public void updatePercents() {
        this.protein.setPercent(this.protein.getValue() * PROTEIN_CALORIES  / this.calories);
        this.fat.setPercent(this.fat.getValue() * FAT_CALORIES  / this.calories);
        this.carbs.setPercent(100 - this.protein.getPercent() - this.fat.getPercent());
    }
}
