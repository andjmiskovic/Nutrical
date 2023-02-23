package com.example.diplomski.service;

import com.example.diplomski.dto.BasicUserData;
import com.example.diplomski.exceptions.NutritionistNotFoundException;
import com.example.diplomski.model.Client;
import com.example.diplomski.model.Nutritionist;
import com.example.diplomski.repository.NutritionistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class NutritionistService {
    private NutritionistRepository nutritionistRepository;

    public Nutritionist getByEmail(String email) throws NutritionistNotFoundException {
        return nutritionistRepository.findById(email).orElseThrow(() -> new NutritionistNotFoundException("Nutritionist not found."));
    }

    public void save(Nutritionist nutritionist) {
        nutritionistRepository.save(nutritionist);
    }

    public List<BasicUserData> getClients(String email) throws NutritionistNotFoundException {
        Nutritionist nutritionist = getByEmail(email);
        List<BasicUserData> clients = new ArrayList<>();
        for (Client client : nutritionist.getClients()) {
            clients.add(BasicUserData.builder()
                    .name(client.getName())
                    .surname(client.getSurname())
                    .email(client.getEmail())
                    .build());
        }
        return clients;
    }
}
