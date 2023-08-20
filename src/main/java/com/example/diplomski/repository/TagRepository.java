package com.example.diplomski.repository;

import com.example.diplomski.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Meal, Long> {
}
