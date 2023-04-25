package com.example.diplomski.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EatenFood {
    @Id
    private Long id;
    @ManyToOne
    private FoodItem foodItem;
    private Double quantity;

    public EatenFood(FoodItem foodItem, Double amount) {
        this.foodItem = foodItem;
        this.quantity = amount;
    }
}
