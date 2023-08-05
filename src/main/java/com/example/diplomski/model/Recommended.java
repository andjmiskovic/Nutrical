package com.example.diplomski.model;

import com.example.diplomski.enums.HealthStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recommended {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double man;
    private Double woman;
    private Double pregnant;
    private Double breastfeeding;
    private Double max;
    private Double recommendedPerKg;

    public Double getValue(ClientData clientData) {
        if (recommendedPerKg != null) {
            return recommendedPerKg * clientData.getWeight();
        }
        return getValue(clientData.getHealthStatus());
    }

    private Double getValue(HealthStatus healthStatus) {
        switch (healthStatus) {
            case MAN -> {
                return man;
            }
            case WOMAN -> {
                return woman;
            }
            case PREGNANT -> {
                return pregnant;
            }
            default -> {
                return breastfeeding;
            }
        }
    }
}
