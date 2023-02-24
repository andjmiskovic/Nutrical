package com.example.diplomski.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.HashMap;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    @Id
    private Long id;
    private String tag;
    private HashMap<FoodItem, Double> eatenFood;
}
