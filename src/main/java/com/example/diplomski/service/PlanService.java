package com.example.diplomski.service;

import com.example.diplomski.dto.ClientAddFoodRequest;
import com.example.diplomski.model.*;
import com.example.diplomski.repository.PlanRepository;
import com.example.diplomski.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PlanService {
    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private FoodService foodService;
    @Autowired
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
                tag.getEatenFood().add(new EatenFood(foodItem, addFoodRequest.getAmount()));
                return;
            }
        }
        Tag newTag = Tag.builder()
                .tag("Tag " + (tags.size() + 1))
                .eatenFood(new ArrayList<>() {{
                    add(new EatenFood(foodItem, addFoodRequest.getAmount()));
                }})
                .build();
        tags.add(newTag);
        tagRepository.save(newTag);
    }
}
