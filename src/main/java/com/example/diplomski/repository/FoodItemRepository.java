package com.example.diplomski.repository;

import com.example.diplomski.model.FoodItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
    Optional<FoodItem> findByName(String name);
    List<FoodItem> findByNameContaining(String name, Pageable pageable);
}
