package com.example.diplomski.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RemoveTagRequest {
    private Long tagId;
    private Long planId;
    private int day;
}
