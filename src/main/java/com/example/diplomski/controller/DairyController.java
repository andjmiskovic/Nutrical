package com.example.diplomski.controller;

import com.example.diplomski.dto.AddFoodRequest;
import com.example.diplomski.dto.RemoveFoodRequest;
import com.example.diplomski.dto.RemoveTagRequest;
import com.example.diplomski.dto.TagRequest;
import com.example.diplomski.exceptions.UserNotFoundException;
import com.example.diplomski.model.DailyPlan;
import com.example.diplomski.service.DairyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceNotFoundException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/dairy")
public class DairyController {

    private DairyService dairyService;

    @GetMapping("/get-plan/{clientEmail}")
    public DailyPlan getPlan(@PathVariable String clientEmail) throws UserNotFoundException {
        return dairyService.getPlan(clientEmail);
    }

    @PostMapping("/add-food")
    public void addFood(@RequestBody AddFoodRequest addFoodRequest) throws UserNotFoundException, InstanceNotFoundException {
        dairyService.addFood(addFoodRequest);
    }

    @PostMapping("/remove-food")
    public void removeFood(@RequestBody RemoveFoodRequest removeFoodRequest) throws UserNotFoundException, InstanceNotFoundException {
        dairyService.removeFood(removeFoodRequest);
    }

    @PostMapping("/add-tag")
    public void addTag(@RequestBody TagRequest addTagRequest) throws InstanceNotFoundException {
        dairyService.addTag(addTagRequest);
    }

    @PostMapping("/rename-tag")
    public void renameTag(@RequestBody TagRequest renameTagRequest) throws InstanceNotFoundException {
        dairyService.renameTag(renameTagRequest);
    }

    @PostMapping("/remove-tag")
    public void removeTag(@RequestBody RemoveTagRequest removeTagRequest) throws InstanceNotFoundException {
        dairyService.removeTag(removeTagRequest);
    }
}