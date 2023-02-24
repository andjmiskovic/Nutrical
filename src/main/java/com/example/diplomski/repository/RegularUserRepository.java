package com.example.diplomski.repository;

import com.example.diplomski.model.RegularUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegularUserRepository extends JpaRepository<RegularUser, String> {
}
