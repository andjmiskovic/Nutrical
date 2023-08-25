package com.example.diplomski.service;

import com.example.diplomski.dto.*;
import com.example.diplomski.exceptions.UserNotFoundException;
import com.example.diplomski.model.*;
import com.example.diplomski.repository.DairyRepository;
import com.example.diplomski.repository.PlanRepository;
import com.example.diplomski.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DairyService {
    @Autowired
    private DairyRepository dairyRepository;
    @Autowired
    private RegularUserService regularUserService;
    @Autowired
    private FoodService foodService;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private PlanRepository planRepository;

    public DailyPlan getDailyPlan(String email) throws InstanceNotFoundException {
        return dairyRepository.findByUserEmail(email).orElseThrow(() -> new InstanceNotFoundException("Plan not found."));
    }

    public DailyPlan getDailyPlan(Long id) throws InstanceNotFoundException {
        return dairyRepository.findById(id).orElseThrow(() -> new InstanceNotFoundException("Plan not found."));
    }

    public DailyPlan getDailyPlan(Long planId, int day) throws InstanceNotFoundException {
        return planRepository.findById(planId).orElseThrow(() -> new InstanceNotFoundException("Plan not found.")).getDailyPlans().get(day - 1);
    }

    public void addFood(AddFoodRequest addFoodRequest) throws UserNotFoundException, InstanceNotFoundException {
        RegularUser regularUser = regularUserService.getByEmail(addFoodRequest.getEmail());
        if (regularUser.getDailyPlanByDate(addFoodRequest.getDate()) == null) {
            regularUser.getDailyDairy().add(new DailyDairy(addFoodRequest.getDate(), new DailyPlan()));
        }
        addFoodToDailyPlan(regularUser, addFoodRequest);
        regularUserService.save(regularUser);
    }

    private void addFoodToDailyPlan(RegularUser regularUser, AddFoodRequest addFoodRequest) throws InstanceNotFoundException {
        FoodItem foodItem = foodService.getFoodByName(addFoodRequest.getFood());
//        DailyPlan dailyPlan = regularUser.getDailyPlanByDate(addFoodRequest.getDate());
//        List<Tag> tags = dailyPlan.getTags();
//        for (Tag tag : tags) {
//            if (Objects.equals(tag.getId(), addFoodRequest.getTagId())) {
//                tag.getEatenFood().add(new EatenFood(foodItem, addFoodRequest.getAmount()));
//                tagRepository.save(tag);
//                return;
//            }
//        }
//        Tag newTag = Tag.builder()
//                .tag("Tag " + (tags.size() + 1))
//                .eatenFood(new ArrayList<>() {{
//                    add(new EatenFood(foodItem, addFoodRequest.getAmount()));
//                }})
//                .build();
//        tags.add(newTag);
//        tagRepository.save(newTag);
//        dairyRepository.save(dailyPlan);
    }

    public void removeFood(RemoveFoodRequest removeFoodRequest) throws UserNotFoundException, InstanceNotFoundException {
        RegularUser regularUser = regularUserService.getByEmail(removeFoodRequest.getEmail());
        DailyPlan dailyPlan = regularUser.getDailyPlanByDate(removeFoodRequest.getDate());
        List<Meal> meals = dailyPlan.getMeals();
        for (Meal meal : meals) {
            if (meal.getId().equals(removeFoodRequest.getTagId())) {
                removeEatenFood(meal, removeFoodRequest.getFood());
                return;
            }
        }
    }

    private void removeEatenFood(Meal meal, String food) throws InstanceNotFoundException {
        FoodItem foodItem = foodService.getFoodByName(food);
        for (EatenFood eatenFood : meal.getEatenFood()) {
            if (eatenFood.getFoodItem().getName().equals(foodItem.getName())) {
                meal.getEatenFood().remove(eatenFood);
                tagRepository.save(meal);
                return;
            }
        }
    }

    public void addTag(TagRequest addTagRequest) throws InstanceNotFoundException {
        DailyPlan dailyPlan = getDailyPlan(addTagRequest.getPlanId());
        Meal meal = new Meal();
        meal.setName(addTagRequest.getTagName());
        meal.setEatenFood(new ArrayList<>());
        tagRepository.save(meal);

        dailyPlan.getMeals().add(meal);
        dairyRepository.save(dailyPlan);
    }

    public void renameTag(TagRequest renameTagRequest) throws InstanceNotFoundException {
        if (tagRepository.findById(renameTagRequest.getTagId()).isPresent()) {
            Meal meal = tagRepository.findById(renameTagRequest.getTagId()).get();
            meal.setName(renameTagRequest.getTagName());
            tagRepository.save(meal);
        } else {
            throw new InstanceNotFoundException("Tag not found.");
        }
    }

    public void removeTag(RemoveTagRequest removeTagRequest) {
        Plan plan = planRepository.findById(removeTagRequest.getPlanId()).get();
        DailyPlan dailyPlan = plan.getDailyPlans().get(removeTagRequest.getDay() - 1);
        if (tagRepository.findById(removeTagRequest.getTagId()).isPresent()) {
            Meal meal = tagRepository.findById(removeTagRequest.getTagId()).get();
            dailyPlan.getMeals().remove(meal);
            dairyRepository.save(dailyPlan);
            tagRepository.delete(meal);
        }
    }

    public void addTraining(TrainingRequest trainingRequest) throws InstanceNotFoundException {
        DailyPlan dailyPlan = getDailyPlan(trainingRequest.getPlanId());
        dailyPlan.setTraining(trainingRequest.getTraining());
        dairyRepository.save(dailyPlan);
    }

    public void addNotes(TrainingRequest trainingRequest) throws InstanceNotFoundException {
        DailyPlan dailyPlan = getDailyPlan(trainingRequest.getPlanId());
        dailyPlan.setNotes(trainingRequest.getTraining());
        dairyRepository.save(dailyPlan);
    }
}
