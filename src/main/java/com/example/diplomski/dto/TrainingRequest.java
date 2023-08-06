package com.example.diplomski.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrainingRequest {
    private String training;
    private Long planId;
    private int day;
}
