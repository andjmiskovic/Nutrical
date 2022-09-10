package com.example.diplomski.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DailyDairy {
    // mozda izbaciti
    private Date date;
    private Double calories;
    private ArrayList<EatenFood> food;
}
