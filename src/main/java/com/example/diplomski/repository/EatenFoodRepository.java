package com.example.diplomski.repository;

import com.example.diplomski.model.EatenFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EatenFoodRepository extends JpaRepository<EatenFood, Long> {
}