package com.example.diplomski.controller;

import com.example.diplomski.dto.ClientAddFoodRequest;
import com.example.diplomski.service.PlanService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/client")
public class ClientController {

    private PlanService planService;

    @PostMapping("/add-food")
    public void addFood(@RequestBody ClientAddFoodRequest addFoodRequest) {
        planService.addFood(addFoodRequest);
    }
}
