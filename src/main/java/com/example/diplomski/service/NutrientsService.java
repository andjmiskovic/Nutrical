package com.example.diplomski.service;

import com.example.diplomski.enums.NutrientKind;
import com.example.diplomski.model.*;
import com.example.diplomski.repository.DairyRepository;
import com.example.diplomski.repository.NutrientRepository;
import com.example.diplomski.repository.RecommendedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.webjars.NotFoundException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.List;

@Service
public class NutrientsService {
    @Autowired
    private DairyRepository dairyRepository;
    @Autowired
    private NutrientRepository nutrientRepository;
    @Autowired
    private RecommendedRepository recommendedRepository;

//    NutrientsService() {
//        loadNutrients();
//    }

    public void loadNutrients() {
        try {
            File file = new File("src/main/resources/static/nutrients.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("nutrient");
            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    Nutrient nutrient = new Nutrient();

                    nutrient.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
                    nutrient.setFunction(eElement.getElementsByTagName("function").item(0).getTextContent());
                    nutrient.setSources(eElement.getElementsByTagName("sources").item(0).getTextContent());
                    nutrient.setKind(NutrientKind.valueOf(eElement.getElementsByTagName("kind").item(0).getTextContent()));
                    nutrient.setSymbol(eElement.getElementsByTagName("symbol").item(0).getTextContent());
                    nutrient.setUnit(eElement.getElementsByTagName("unit").item(0).getTextContent());

                    Recommended recommended = new Recommended();
                    Element e = (Element) eElement.getElementsByTagName("recommended").item(0);

                    recommended.setMan(Double.valueOf(e.getElementsByTagName("man").item(0).getTextContent()));
                    recommended.setWoman(Double.valueOf(e.getElementsByTagName("woman").item(0).getTextContent()));
                    recommended.setPregnant(Double.valueOf(e.getElementsByTagName("pregnant").item(0).getTextContent()));
                    recommended.setBreastfeeding(Double.valueOf(e.getElementsByTagName("breastfeeding").item(0).getTextContent()));

                    double max = Double.POSITIVE_INFINITY;
                    try {
                        max = Double.parseDouble(e.getElementsByTagName("max").item(0).getTextContent());
                    } catch (Exception ignored) {
                    }
                    recommended.setMax(max);
                    recommendedRepository.save(recommended);

                    nutrient.setRecommended(recommended);
                    nutrientRepository.save(nutrient);
                }
            }
            System.out.println("Nutrients loaded.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Nutrient> getNutrients() {
        return nutrientRepository.findAll();
    }

    public HashMap<Nutrient, Double> createEmptyNutrientMap() {
        HashMap<Nutrient, Double> hashMap = new HashMap<>();
        for (Nutrient nutrient : getNutrients()) {
            hashMap.put(nutrient, 0.0);
        }
        return hashMap;
    }

    public HashMap<Nutrient, Double> getNutrients(Long planId) {
        HashMap<Nutrient, Double> emptyNutrients = createEmptyNutrientMap();
        if (dairyRepository.findById(planId).isPresent()) {
            DailyPlan dailyPlan = dairyRepository.findById(planId).get();
            for (Tag tag : dailyPlan.getTags()) {
                for (EatenFood food : tag.getEatenFood()) {
                    addFoodNutrients(emptyNutrients, food.getFoodItem(), food.getQuantity());
                }
            }
        }
        return emptyNutrients;
    }

    private void addFoodNutrients(HashMap<Nutrient, Double> nutrients, FoodItem foodItem, Double amount) {
        nutrients.replaceAll((nutrient, value) -> nutrients.get(nutrient) + getNutrientQuantity(foodItem, nutrient) * amount / 100);
    }

    private Double getNutrientQuantity(FoodItem foodItem, Nutrient nutrient) {
        for (NutrientQuantity nutrientQuantity : foodItem.getNutrients()) {
            if (nutrientQuantity.getNutrient().equals(nutrient.getName())) {
                return nutrientQuantity.getQuantity();
            }
        }
        throw new NotFoundException("Food item not found.");
    }
}
