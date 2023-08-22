package com.example.diplomski.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlanEmailRequest {
    private String text;
    private Long planId;
}
