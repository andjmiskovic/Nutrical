package com.example.diplomski.service;

import com.example.diplomski.dto.BasicUserData;
import com.example.diplomski.exceptions.UserNotFoundException;
import com.example.diplomski.model.Client;
import com.example.diplomski.model.Nutritionist;
import com.example.diplomski.repository.NutritionistRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NutritionistService {
    private NutritionistRepository nutritionistRepository;

    public Nutritionist getByEmail(String email) throws UserNotFoundException {
        return nutritionistRepository.findById(email).orElseThrow(() -> new UserNotFoundException("Nutritionist not found."));
    }

    public void save(Nutritionist nutritionist) {
        nutritionistRepository.save(nutritionist);
    }

    public List<BasicUserData> getClients(String email, String search) throws UserNotFoundException {
        Nutritionist nutritionist = getByEmail(email);
        List<BasicUserData> clients = new ArrayList<>();
        for (Client client : nutritionist.getClients()) {
            if (client.getEmail().toLowerCase().contains(search.toLowerCase())) {
                clients.add(BasicUserData.builder()
                        .name(client.getName())
                        .surname(client.getSurname())
                        .email(client.getEmail())
                        .build());
            }
        }
        return clients;
    }

    public Client getClient(String email, String clientEmail) throws UserNotFoundException {
        Nutritionist nutritionist = getByEmail(email);
        for (Client client : nutritionist.getClients()) {
            if (client.getEmail().equals(clientEmail)) {
                return client;
            }
        }
        throw new UserNotFoundException("Client not found.");
    }
}
