package com.example.diplomski.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DailyDairy {
    @Id
    private Long id;
    private Date date;
    @ManyToOne
    private DailyPlan dailyPlan;

    public DailyDairy(Date date, DailyPlan dailyPlan) {
        this.date = date;
        this.dailyPlan = dailyPlan;
    }
}
