package com.example.diplomski.model;

import com.example.diplomski.enums.NutrientKind;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Nutrient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String symbol;
    @Lob
    private String function;
    @Lob
    private String sources;
    private String unit;
    private NutrientKind kind;
    @OneToOne
    private Recommended recommended;
}
