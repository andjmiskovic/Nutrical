package com.example.diplomski.service;

import com.example.diplomski.dto.BasicUserData;
import com.example.diplomski.dto.ClientDetails;
import com.example.diplomski.exceptions.UserNotFoundException;
import com.example.diplomski.model.Client;
import com.example.diplomski.model.Nutritionist;
import com.example.diplomski.model.Plan;
import com.example.diplomski.repository.NutritionistRepository;
import com.example.diplomski.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NutritionistService {
    @Autowired
    private NutritionistRepository nutritionistRepository;
    @Autowired
    private PlanRepository planRepository;

    public Nutritionist getByEmail(String email) throws UserNotFoundException {
        return nutritionistRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("Nutritionist not found."));
    }

    public void save(Nutritionist nutritionist) {
        nutritionistRepository.save(nutritionist);
    }

    public List<ClientDetails> getClients(String email) throws UserNotFoundException {
        Nutritionist nutritionist = getByEmail(email);
        List<ClientDetails> clients = new ArrayList<>();
        for (Client client : nutritionist.getClients()) {
            clients.add(ClientDetails.builder()
                    .name(client.getName())
                    .surname(client.getSurname())
                    .email(client.getEmail())
                    .planId(getPlanId(client, nutritionist))
                    .clientData(client.getClientData())
                    .build());
        }
        return clients;
    }

    private Long getPlanId(Client client, Nutritionist nutritionist) {
        Plan plan = planRepository.findByClientEmail(client.getEmail());
        if (plan == null) {
            plan = new Plan();
            plan.setClient(client);
            plan.setNutritionist(nutritionist);
            plan.setDailyPlans(new ArrayList<>());
            planRepository.save(plan);
        }
        return plan.getId();
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
