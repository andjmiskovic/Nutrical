package com.example.diplomski.service;

import com.example.diplomski.dto.ClientAddFoodRequest;
import com.example.diplomski.model.*;
import com.example.diplomski.repository.PlanRepository;
import com.example.diplomski.repository.TagRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PlanService {
    private PlanRepository planRepository;
    private FoodService foodService;
    private TagRepository tagRepository;

    public void addFood(ClientAddFoodRequest addFoodRequest) {
        Plan plan = planRepository.findByClientEmail(addFoodRequest.getClientEmail());
        DailyPlan dailyPlan = plan.getDailyPlans().get(addFoodRequest.getDay());
    }

    private void addFoodToDailyPlan(DailyPlan dailyPlan, ClientAddFoodRequest addFoodRequest) throws InstanceNotFoundException {
        FoodItem foodItem = foodService.getFoodByName(addFoodRequest.getFood());
        List<Tag> tags = dailyPlan.getTags();
        for (Tag tag : dailyPlan.getTags()) {
            if (Objects.equals(tag.getId(), addFoodRequest.getTagId())) {
                tag.getEatenFood().put(foodItem, addFoodRequest.getAmount());
                return;
            }
        }
        Tag newTag = Tag.builder()
                .tag("Tag " + (tags.size() + 1))
                .eatenFood(new HashMap<>() {{
                    put(foodItem, addFoodRequest.getAmount());
                }})
                .build();
        tags.add(newTag);
        tagRepository.save(newTag);
    }
}
