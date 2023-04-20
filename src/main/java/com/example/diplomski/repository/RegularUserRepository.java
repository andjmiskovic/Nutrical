package com.example.diplomski.repository;

import com.example.diplomski.model.RegularUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegularUserRepository extends JpaRepository<RegularUser, String> {
}
