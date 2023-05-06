package com.example.diplomski.repository;

import com.example.diplomski.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    Optional<User> findByVerificationCode(String verificationCode);

    Boolean existsByEmail(String email);
}