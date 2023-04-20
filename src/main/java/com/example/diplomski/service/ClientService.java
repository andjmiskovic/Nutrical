package com.example.diplomski.service;

import com.example.diplomski.dto.ClientRequest;
import com.example.diplomski.exceptions.UserNotFoundException;
import com.example.diplomski.model.Client;
import com.example.diplomski.model.ClientData;
import com.example.diplomski.model.DailyPlan;
import com.example.diplomski.model.Nutritionist;
import com.example.diplomski.repository.ClientRepository;
import com.example.diplomski.repository.DairyRepository;
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

        DailyPlan dailyPlan = new DailyPlan();
        dailyPlan.setUserEmail(client.getEmail());
        dailyPlan.setTags(new ArrayList<>());
        dailyPlanRepository.save(dailyPlan);
    }
}
