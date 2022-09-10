package com.example.diplomski.service;

import com.example.diplomski.model.Nutrient;
import com.example.diplomski.repository.NutrientsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NutrientsService {

    public ArrayList<Nutrient> getNutrients() {
        return NutrientsRepository.getNutrients();
    }
}
