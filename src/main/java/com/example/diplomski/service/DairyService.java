package com.example.diplomski.service;

import com.example.diplomski.dto.AddFoodRequest;
import com.example.diplomski.dto.RemoveFoodRequest;
import com.example.diplomski.dto.RemoveTagRequest;
import com.example.diplomski.dto.TagRequest;
import com.example.diplomski.exceptions.UserNotFoundException;
import com.example.diplomski.model.DailyPlan;
import com.example.diplomski.model.FoodItem;
import com.example.diplomski.model.RegularUser;
import com.example.diplomski.model.Tag;
import com.example.diplomski.repository.DairyRepository;
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
public class DairyService {
    private DairyRepository dairyRepository;
    private RegularUserService regularUserService;
    private FoodService foodService;
    private TagRepository tagRepository;

    public DailyPlan getDailyPlan(String email) throws InstanceNotFoundException {
        return dairyRepository.findByUserEmail(email).orElseThrow(() -> new InstanceNotFoundException("Plan not found."));
    }

    public DailyPlan getDailyPlan(Long id) throws InstanceNotFoundException {
        return dairyRepository.findById(id).orElseThrow(() -> new InstanceNotFoundException("Plan not found."));
    }

    public void addFood(AddFoodRequest addFoodRequest) throws UserNotFoundException, InstanceNotFoundException {
        RegularUser regularUser = regularUserService.getByEmail(addFoodRequest.getEmail());
        if (!regularUser.getDailyDairy().containsKey(addFoodRequest.getDate())) {
            regularUser.getDailyDairy().put(addFoodRequest.getDate(), new DailyPlan());
        }
        addFoodToDailyPlan(regularUser, addFoodRequest);
        regularUserService.save(regularUser);
    }

    private void addFoodToDailyPlan(RegularUser regularUser, AddFoodRequest addFoodRequest) throws InstanceNotFoundException {
        FoodItem foodItem = foodService.getFoodByName(addFoodRequest.getFood());
        DailyPlan dailyPlan = regularUser.getDailyDairy().get(addFoodRequest.getDate());
        List<Tag> tags = dailyPlan.getTags();
        for (Tag tag : tags) {
            if (Objects.equals(tag.getId(), addFoodRequest.getTagId())) {
                tag.getEatenFood().put(foodItem, addFoodRequest.getAmount());
                tagRepository.save(tag);
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
        dairyRepository.save(dailyPlan);
    }

    public void removeFood(RemoveFoodRequest removeFoodRequest) throws UserNotFoundException, InstanceNotFoundException {
        RegularUser regularUser = regularUserService.getByEmail(removeFoodRequest.getEmail());
        DailyPlan dailyPlan = regularUser.getDailyDairy().get(removeFoodRequest.getDate());
        List<Tag> tags = dailyPlan.getTags();
        for (Tag tag : tags) {
            if (tag.getId().equals(removeFoodRequest.getTagId())) {
                tag.getEatenFood().remove(foodService.getFoodByName(removeFoodRequest.getFood()));
                tagRepository.save(tag);
                regularUserService.save(regularUser);
                dairyRepository.save(dailyPlan);
                return;
            }
        }
    }

    public void addTag(TagRequest addTagRequest) throws InstanceNotFoundException {
        DailyPlan dailyPlan = getDailyPlan(addTagRequest.getDailyPlanId());
        Tag tag = Tag.builder()
                .tag(addTagRequest.getTagName())
                .eatenFood(new HashMap<>())
                .build();
        dailyPlan.getTags().add(tag);
        dairyRepository.save(dailyPlan);
        tagRepository.save(tag);
    }

    public void renameTag(TagRequest renameTagRequest) throws InstanceNotFoundException {
        if (tagRepository.findById(renameTagRequest.getTagId()).isPresent()) {
            Tag tag = tagRepository.findById(renameTagRequest.getTagId()).get();
            tag.setTag(renameTagRequest.getTagName());
            tagRepository.save(tag);
        } else {
            throw new InstanceNotFoundException("Tag not found.");
        }
    }

    public void removeTag(RemoveTagRequest removeTagRequest) throws InstanceNotFoundException {
        DailyPlan dailyPlan = getDailyPlan(removeTagRequest.getDailyPlanId());
        if (tagRepository.findById(removeTagRequest.getTagId()).isPresent()) {
            Tag tag = tagRepository.findById(removeTagRequest.getTagId()).get();
            dailyPlan.getTags().remove(tag);
            dairyRepository.save(dailyPlan);
        }
    }

    public DailyPlan getPlan(String clientEmail) throws UserNotFoundException {
        return dairyRepository.findByUserEmail(clientEmail).orElseThrow(() -> new UserNotFoundException("Client or client plan not found."));
    }
}
