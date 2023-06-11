package com.example.diplomski.repository;

import com.example.diplomski.model.Nutrient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutrientRepository extends JpaRepository<Nutrient, Long> {
}
