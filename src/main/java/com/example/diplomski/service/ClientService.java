package com.example.diplomski.service;

import com.example.diplomski.dto.ClientRequest;
import com.example.diplomski.exceptions.NutritionistNotFoundException;
import com.example.diplomski.model.Client;
import com.example.diplomski.model.ClientData;
import com.example.diplomski.model.Nutritionist;
import com.example.diplomski.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientService {

    private ClientRepository clientRepository;
    private NutritionistService nutritionistService;

    public void addClient(ClientRequest clientRequestDTO) throws NutritionistNotFoundException {
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
    }
}
