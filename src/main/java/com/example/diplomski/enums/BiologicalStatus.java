package com.example.diplomski.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BiologicalStatus {
    MAN("Man"),
    WOMAN("Woman"),
    PREGNANT("Pregnant"),
    BREASTFEEDING("Breastfeeding");

    private final String name;
}
