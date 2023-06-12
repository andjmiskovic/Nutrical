package com.example.diplomski.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Set;

@Entity
@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FoodItem {
    @Id
    private Long id;
    private String name;
    private Double calories;
    private Double protein;
    private Double fat;
    private Double carbs;
    @OneToMany
    private Set<NutrientQuantity> nutrients;
}
