package com.example.diplomski.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientCalculations {
    ClientDetails clientDetails;
    Double calories;
    Double bmi;
    Integer age;
}
