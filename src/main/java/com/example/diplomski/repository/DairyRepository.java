package com.example.diplomski.repository;

import com.example.diplomski.model.DailyPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DairyRepository extends JpaRepository<DailyPlan, Long> {
    Optional<DailyPlan> findByUserEmail(String userEmail);
}
