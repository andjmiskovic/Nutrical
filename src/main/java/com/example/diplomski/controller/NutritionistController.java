package com.example.diplomski.controller;

import com.example.diplomski.dto.BasicUserData;
import com.example.diplomski.dto.ClientRequest;
import com.example.diplomski.exceptions.UserNotFoundException;
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

    @GetMapping("/get-clients")
    public List<BasicUserData> getClients(@RequestBody String email) throws UserNotFoundException {
        return nutritionistService.getClients(email);
    }
}
