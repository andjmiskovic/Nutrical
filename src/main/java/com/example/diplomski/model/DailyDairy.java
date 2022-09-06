package com.example.diplomski.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class DailyDairy {
    // mozda izbaciti
    private Date date;
    private Double calories;
    private ArrayList<EatenFood> food;
}
