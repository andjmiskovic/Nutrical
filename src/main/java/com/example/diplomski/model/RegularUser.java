package com.example.diplomski.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegularUser extends User {
    @OneToOne(cascade = CascadeType.ALL)
    private ClientData clientData;
    @ManyToMany
    private List<DailyDairy> dailyDairy;

    public DailyPlan getDailyPlanByDate(Date date) {
        for (DailyDairy dairy : dailyDairy) {
            if (dairy.getDate().equals(date))
                return dairy.getDailyPlan();
        }
        return null;
    }
}
