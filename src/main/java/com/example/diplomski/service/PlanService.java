package com.example.diplomski.service;

import com.example.diplomski.dto.ClientAddFoodRequest;
import com.example.diplomski.dto.DailyPlanResponse;
import com.example.diplomski.dto.PlanEmailRequest;
import com.example.diplomski.model.*;
import com.example.diplomski.repository.DairyRepository;
import com.example.diplomski.repository.EatenFoodRepository;
import com.example.diplomski.repository.PlanRepository;
import com.example.diplomski.repository.TagRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;
import java.io.IOException;
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
    @Autowired
    private MailingService mailingService;

    public Plan getPlan(Long id) {
        return planRepository.findById(id).get();
    }

    public DailyPlanResponse getPlan(Long id, int day) throws Exception {
        Plan plan = getPlan(id);
        if (plan == null) {
            throw new Exception("Plan does not exists");
        }
        if (plan.getDailyPlans().size() < day) {
            Meal meal = new Meal();
            meal.setName("Meal 1");
            meal.setEatenFood(new ArrayList<>());
            tagRepository.save(meal);

            DailyPlan dailyPlan = new DailyPlan();
            dailyPlan.setUserEmail(plan.getClient().getEmail());
            dailyPlan.setMeals(List.of(meal));
            dailyPlan.setNotes("");
            dailyPlan.setTraining("");
            dairyRepository.save(dailyPlan);

            plan.getDailyPlans().add(dailyPlan);
            planRepository.save(plan);
        }
        return getDailyPlanResponse(plan, day);
    }

    private DailyPlanResponse getDailyPlanResponse(Plan plan, int day) {
        DailyPlan dailyPlan = plan.getDailyPlans().get(day - 1);
        return DailyPlanResponse.builder()
                .id(dailyPlan.getId())
                .meals(dailyPlan.getMeals())
                .notes(dailyPlan.getNotes())
                .training(dailyPlan.getTraining())
                .userEmail(dailyPlan.getUserEmail())
                .daysInPlan(plan.getDailyPlans().size())
                .build();
    }

    public void addFood(ClientAddFoodRequest addFoodRequest) throws InstanceNotFoundException {
        Plan plan = planRepository.findByClientEmail(addFoodRequest.getClientEmail());
        DailyPlan dailyPlan = plan.getDailyPlans().get(addFoodRequest.getDay() - 1);
        addFoodToDailyPlan(dailyPlan, addFoodRequest);
    }

    private void addFoodToDailyPlan(DailyPlan dailyPlan, ClientAddFoodRequest addFoodRequest) throws InstanceNotFoundException {
        FoodItem foodItem = foodService.getFoodById(addFoodRequest.getFoodId());
        Meal selectedMeal = null;

        for (Meal meal : dailyPlan.getMeals()) {
            if (Objects.equals(meal.getId(), addFoodRequest.getTagId())) {
                selectedMeal = meal;
                break;
            }
        }

        if (selectedMeal != null) {
            EatenFood eaten = new EatenFood();
            eaten.setFoodItem(foodItem);
            eaten.setQuantity(addFoodRequest.getAmount());
            eatenFoodRepository.save(eaten);

            selectedMeal.getEatenFood().add(eaten);
            tagRepository.save(selectedMeal);
        }
    }

    public void sendEmail(PlanEmailRequest planEmailRequest) throws IOException, MessagingException {
        Plan plan = planRepository.findById(planEmailRequest.getPlanId()).get();

        String subject = "Nutrical - Plan by " + plan.getNutritionist().getFirstName() + " " + plan.getNutritionist().getLastName();
        PDFService.createPDF(plan);

        mailingService.sendMailWithAttachment(plan.getClient().getEmail(), planEmailRequest.getText(), subject, PDFService.getFileLocation(plan.getId()));
    }
}
