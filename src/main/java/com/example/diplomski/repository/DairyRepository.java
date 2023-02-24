package com.example.diplomski.repository;

import com.example.diplomski.model.DailyPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DairyRepository extends JpaRepository<DailyPlan, Long> {
}
