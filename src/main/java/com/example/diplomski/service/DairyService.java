package com.example.diplomski.service;

import com.example.diplomski.dto.AddFoodRequest;
import com.example.diplomski.exceptions.FoodItemNotFoundException;
import com.example.diplomski.exceptions.UserNotFoundException;
import com.example.diplomski.model.DailyPlan;
import com.example.diplomski.model.FoodItem;
import com.example.diplomski.model.RegularUser;
import com.example.diplomski.model.Tag;
import com.example.diplomski.repository.DairyRepository;
import com.example.diplomski.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class DairyService {
    private DairyRepository dairyRepository;
    private RegularUserService regularUserService;
    private FoodService foodService;
    private TagRepository tagRepository;

    public void addFood(AddFoodRequest addFoodRequest) throws UserNotFoundException, FoodItemNotFoundException {
        RegularUser regularUser = regularUserService.getByEmail(addFoodRequest.getEmail());
        if (!regularUser.getDailyDairy().containsKey(addFoodRequest.getDate())) {
            regularUser.getDailyDairy().put(addFoodRequest.getDate(), new DailyPlan());
        }
        addFoodToDailyPlan(regularUser, addFoodRequest);
        regularUserService.save(regularUser);
    }

    private void addFoodToDailyPlan(RegularUser regularUser, AddFoodRequest addFoodRequest) throws FoodItemNotFoundException {
        FoodItem foodItem = foodService.getFoodByName(addFoodRequest.getFood());
        List<Tag> tags = regularUser.getDailyDairy().get(addFoodRequest.getDate()).getTags();
        for (Tag tag : tags) {
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
