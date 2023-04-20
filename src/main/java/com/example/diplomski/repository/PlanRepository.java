package com.example.diplomski.repository;

import com.example.diplomski.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
    Plan findByClientEmail(String email);
}
