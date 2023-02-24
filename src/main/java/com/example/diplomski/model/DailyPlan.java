package com.example.diplomski.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DailyPlan {
    @Id
    private Long id;
    private ArrayList<Tag> tags;
    private String notes;
}
