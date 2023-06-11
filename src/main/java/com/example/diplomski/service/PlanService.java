package com.example.diplomski.service;

import com.example.diplomski.dto.ClientAddFoodRequest;
import com.example.diplomski.model.*;
import com.example.diplomski.repository.DairyRepository;
import com.example.diplomski.repository.EatenFoodRepository;
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
    private DairyRepository dairyRepository;
    @Autowired
    private FoodService foodService;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private EatenFoodRepository eatenFoodRepository;

    public Plan getPlan(Long id) {
        return planRepository.findById(id).get();
    }

    public DailyPlan getPlan(Long id, int day) throws Exception {
        Plan plan = getPlan(id);
        if (plan == null) {
            throw new Exception("Plan does not exists");
        }
        if (plan.getDailyPlans().size() < day) {
            Tag tag = new Tag();
            tag.setTag("Meal 1");
            tag.setEatenFood(new ArrayList<>());
            tagRepository.save(tag);

            DailyPlan dailyPlan = new DailyPlan();
            dailyPlan.setUserEmail(plan.getClient().getEmail());
            dailyPlan.setTags(List.of(tag));
            dairyRepository.save(dailyPlan);

            plan.getDailyPlans().add(dailyPlan);
            planRepository.save(plan);
        }
        return plan.getDailyPlans().get(day - 1);
    }

    public void addFood(ClientAddFoodRequest addFoodRequest) throws InstanceNotFoundException {
        Plan plan = planRepository.findByClientEmail(addFoodRequest.getClientEmail());
        DailyPlan dailyPlan = plan.getDailyPlans().get(addFoodRequest.getDay() - 1);
        addFoodToDailyPlan(dailyPlan, addFoodRequest);
    }

    private void addFoodToDailyPlan(DailyPlan dailyPlan, ClientAddFoodRequest addFoodRequest) throws InstanceNotFoundException {
        FoodItem foodItem = foodService.getFoodById(addFoodRequest.getFoodId());
        Tag selectedTag = null;

        for (Tag tag : dailyPlan.getTags()) {
            if (Objects.equals(tag.getId(), addFoodRequest.getTagId())) {
                selectedTag = tag;
                break;
            }
        }

        if (selectedTag != null) {
            EatenFood eaten = new EatenFood();
            eaten.setFoodItem(foodItem);
            eaten.setQuantity(addFoodRequest.getAmount());
            eatenFoodRepository.save(eaten);

            selectedTag.getEatenFood().add(eaten);
            tagRepository.save(selectedTag);
        }
    }
}
