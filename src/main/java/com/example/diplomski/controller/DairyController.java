package com.example.diplomski.controller;

import com.example.diplomski.dto.AddFoodRequest;
import com.example.diplomski.exceptions.FoodItemNotFoundException;
import com.example.diplomski.exceptions.UserNotFoundException;
import com.example.diplomski.service.DairyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/dairy")
public class DairyController {

    private DairyService dairyService;

    @PostMapping("/add-food")
    public void addFood(@RequestBody AddFoodRequest addFoodRequest) throws UserNotFoundException, FoodItemNotFoundException {
        dairyService.addFood(addFoodRequest);
    }
}
