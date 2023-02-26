package com.example.diplomski.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashMap;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegularUser {
    private ClientData clientData;
    private HashMap<Date, DailyPlan> dailyDairy;
}
