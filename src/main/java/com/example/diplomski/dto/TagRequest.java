package com.example.diplomski.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TagRequest {
    private String tagName;
    private Long tagId;
    private Long planId;
    private int day;
}
