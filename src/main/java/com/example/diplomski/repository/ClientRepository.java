package com.example.diplomski.repository;

import com.example.diplomski.model.Client;
import com.example.diplomski.model.ClientData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    Client findByEmail(String email);
}
