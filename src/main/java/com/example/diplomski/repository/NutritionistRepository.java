package com.example.diplomski.repository;

import com.example.diplomski.model.Nutritionist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutritionistRepository extends JpaRepository<Nutritionist, String> {
}
