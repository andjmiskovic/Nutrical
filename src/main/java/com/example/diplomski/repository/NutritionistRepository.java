package com.example.diplomski.repository;

import com.example.diplomski.model.Nutritionist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NutritionistRepository extends JpaRepository<Nutritionist, String> {
    Optional<Nutritionist> findByEmail(String email);
}
