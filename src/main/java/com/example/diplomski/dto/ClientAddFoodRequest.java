package com.example.diplomski.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
@Builder
public class ClientAddFoodRequest {
    private String clientEmail;
    private Long planId;
    private Long tagId;
    private int day;
    private Long foodId;
    private Double amount;
}
