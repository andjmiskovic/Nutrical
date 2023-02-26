package com.example.diplomski.repository;

import com.example.diplomski.model.DailyPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DairyRepository extends JpaRepository<DailyPlan, Long> {
    Optional<DailyPlan> findByUserEmail(String userEmail);
}
