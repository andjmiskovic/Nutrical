package com.example.diplomski.controller;

import com.example.diplomski.dto.BasicUserData;
import com.example.diplomski.dto.ClientDetails;
import com.example.diplomski.dto.ClientRequest;
import com.example.diplomski.exceptions.UserNotFoundException;
import com.example.diplomski.model.Client;
import com.example.diplomski.service.ClientService;
import com.example.diplomski.service.NutritionistService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/nutritionist")
public class NutritionistController {

    private final ClientService clientService;
    private final NutritionistService nutritionistService;

    @PostMapping("/add-client")
    public void addClient(@RequestBody ClientRequest clientRequestDTO) throws UserNotFoundException {
        clientService.addClient(clientRequestDTO);
    }

    @GetMapping("/get-clients/{nutritionistEmail}")
    public List<ClientDetails> getClients(@PathVariable("nutritionistEmail") String nutritionistEmail) throws UserNotFoundException {
        return nutritionistService.getClients(nutritionistEmail);
    }

    @GetMapping("/get-client/{nutritionistEmail}/{clientEmail}")
    public Client getClient(@PathVariable("nutritionistEmail") String nutritionistEmail, @PathVariable("clientEmail") String clientEmail) throws UserNotFoundException {
        return nutritionistService.getClient(nutritionistEmail, clientEmail);
    }
}
