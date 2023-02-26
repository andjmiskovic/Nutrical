package com.example.diplomski.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class RemoveTagRequest {
    private Long tagId;
    private Long dailyPlanId;
}
