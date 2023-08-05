package com.example.diplomski.service;

import com.example.diplomski.dto.ClientCalculations;
import com.example.diplomski.dto.ClientDetails;
import com.example.diplomski.dto.ClientRequest;
import com.example.diplomski.exceptions.UserNotFoundException;
import com.example.diplomski.model.*;
import com.example.diplomski.repository.ClientRepository;
import com.example.diplomski.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private NutritionistService nutritionistService;
    @Autowired
    private PlanRepository planRepository;

    public Client getClientByEmail(String email) {
        return clientRepository.findByEmail(email);
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
        client.getClientData().setCalorieGoal(ClientUtils.calculateCalories(client.getClientData()));
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

    public void editClient(ClientRequest clientRequestDTO) {
        Client client = getClientByEmail(clientRequestDTO.getEmail());
        client.setName(clientRequestDTO.getName());
        client.setSurname(clientRequestDTO.getSurname());
        client.setClientData(ClientData.builder()
                .dateOfBirth(clientRequestDTO.getDateOfBirth())
                .activityStatus(clientRequestDTO.getActivityStatus())
                .healthStatus(clientRequestDTO.getHealthStatus())
                .height(clientRequestDTO.getHeight())
                .weight(clientRequestDTO.getWeight())
                .carbsPercent(clientRequestDTO.getCarbsPercent())
                .fatPercent(clientRequestDTO.getFatPercent())
                .proteinPercent(clientRequestDTO.getProteinPercent())
                .calorieGoal(clientRequestDTO.getCalorieGoal())
                .build());
        clientRepository.save(client);
    }

    public ClientCalculations getClientCalculations(Client client) {
        return ClientCalculations.builder()
                .clientDetails(getClientDetails(client))
                .age(ClientUtils.calculateAge(client.getClientData().getDateOfBirth()))
                .calories(ClientUtils.calculateCalories(client.getClientData()))
                .bmi(ClientUtils.calculateBMI(client.getClientData()))
                .build();
    }

    public ClientDetails getClientDetails(Client client) {
        return ClientDetails.builder()
                .name(client.getName())
                .surname(client.getSurname())
                .email(client.getEmail())
                .planId(planRepository.findByClientEmail(client.getEmail()).getId())
                .clientData(client.getClientData())
                .build();
    }
}
