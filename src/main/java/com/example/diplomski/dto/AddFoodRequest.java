package com.example.diplomski.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Builder
public class AddFoodRequest {
    private String email;
    private Date date;
    private Long tagId;
    private String food;
    private Double amount;
}
