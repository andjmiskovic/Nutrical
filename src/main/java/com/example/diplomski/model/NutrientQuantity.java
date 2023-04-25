package com.example.diplomski.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NutrientQuantity {
    @Id
    private Long id;
    private String nutrient;
    private Double quantity;
}
