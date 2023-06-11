package com.example.diplomski.controller;

import com.example.diplomski.dto.*;
import com.example.diplomski.exceptions.UserNotFoundException;
import com.example.diplomski.model.DailyPlan;
import com.example.diplomski.model.Plan;
import com.example.diplomski.service.DairyService;
import com.example.diplomski.service.PlanService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceNotFoundException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/dairy")
public class PlanController {

    private DairyService dairyService;
    private PlanService planService;

    @GetMapping("/get-plan/{planId}")
    public Plan getPlan(@PathVariable Long planId) {
        return planService.getPlan(planId);
    }

    @GetMapping("/get-plan/{planId}/{day}")
    public DailyPlan getPlan(@PathVariable Long planId, @PathVariable int day) throws Exception {
        return planService.getPlan(planId, day);
    }

    @PostMapping("/add-food")
    public void addFood(@RequestBody ClientAddFoodRequest addFoodRequest) throws InstanceNotFoundException {
        planService.addFood(addFoodRequest);
    }

    @PostMapping("/add-food-regular-user")
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
