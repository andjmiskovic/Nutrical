package com.example.diplomski.repository;

import com.example.diplomski.model.NutrientQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutrientQuantityRepository extends JpaRepository<NutrientQuantity, Long> {
}