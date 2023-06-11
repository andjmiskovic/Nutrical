package com.example.diplomski.service;

import com.example.diplomski.dto.ClientRequest;
import com.example.diplomski.exceptions.UserNotFoundException;
import com.example.diplomski.model.*;
import com.example.diplomski.repository.ClientRepository;
import com.example.diplomski.repository.DairyRepository;
import com.example.diplomski.repository.PlanRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ClientService {
    private ClientRepository clientRepository;
    private NutritionistService nutritionistService;
    private DairyRepository dailyPlanRepository;
    private PlanRepository planRepository;

    public Client getClientByEmail(String email) throws UserNotFoundException {
        return clientRepository.findById(email).orElseThrow(() -> new UserNotFoundException("Client not found."));
    }

    public void addClient(ClientRequest clientRequestDTO) throws UserNotFoundException {
        Client client = Client.builder()
                .email(clientRequestDTO.getEmail())
                .name(clientRequestDTO.getName())
                .surname(clientRequestDTO.getSurname())
                .clientData(ClientData.builder()
                        .dateOfBirth(clientRequestDTO.getDateOfBirth())
                        .activityStatus(clientRequestDTO.getActivityStatus())
                        .healthStatus(clientRequestDTO.getHealthStatus())
                        .height(clientRequestDTO.getHeight())
                        .weight(clientRequestDTO.getWeight())
                        .build())
                .build();
        clientRepository.save(client);

        Nutritionist nutritionist = nutritionistService.getByEmail(clientRequestDTO.getNutritionistEmail());
        nutritionist.getClients().add(client);
        nutritionistService.save(nutritionist);

        Plan plan = new Plan();
        plan.setDailyPlans(new ArrayList<>());
        plan.setNutritionist(nutritionist);
        plan.setClient(client);
        planRepository.save(plan);
    }
}
