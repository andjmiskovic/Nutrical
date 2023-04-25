package com.example.diplomski.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DailyPlan {
    @Id
    private Long id;
    @ManyToMany
    private List<Tag> tags;
    private String notes;
    private String userEmail;
}
