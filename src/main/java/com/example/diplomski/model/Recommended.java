package com.example.diplomski.model;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recommended {
    Double man;
    Double woman;
    Double pregnant;
    Double breastfeeding;
    Double max;
}
