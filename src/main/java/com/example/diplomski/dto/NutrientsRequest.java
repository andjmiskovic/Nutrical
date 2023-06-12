package com.example.diplomski.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NutrientsRequest {
    private Long planId;
    private int day;
}