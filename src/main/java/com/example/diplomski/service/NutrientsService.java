package com.example.diplomski.service;

import com.example.diplomski.dto.NutrientScore;
import com.example.diplomski.dto.NutrientsRequest;
import com.example.diplomski.dto.NutrientsResponse;
import com.example.diplomski.enums.NutrientKind;
import com.example.diplomski.model.*;
import com.example.diplomski.repository.ClientRepository;
import com.example.diplomski.repository.NutrientRepository;
import com.example.diplomski.repository.PlanRepository;
import com.example.diplomski.repository.RecommendedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.List;

@Service
public class NutrientsService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private NutrientRepository nutrientRepository;
    @Autowired
    private RecommendedRepository recommendedRepository;
    @Autowired
    private PlanRepository planRepository;

    private static final String NUTRIENTS_FILE = "src/main/resources/static/nutrients.xml";

    public void loadNutrients() {
        try {
            File file = new File(NUTRIENTS_FILE);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            Document doc = dbf.newDocumentBuilder().parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("nutrient");
            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    loadNutrientData((Element) node);
                }
            }
            System.out.println("Nutrients loaded.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadNutrientData(Element eElement) {
        Nutrient nutrient = new Nutrient();

        nutrient.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
        nutrient.setFunction(eElement.getElementsByTagName("function").item(0).getTextContent());
        nutrient.setSources(eElement.getElementsByTagName("sources").item(0).getTextContent());
        nutrient.setKind(NutrientKind.valueOf(eElement.getElementsByTagName("kind").item(0).getTextContent()));
        nutrient.setSymbol(eElement.getElementsByTagName("symbol").item(0).getTextContent());
        nutrient.setUnit(eElement.getElementsByTagName("unit").item(0).getTextContent());

        Recommended recommended = loadRecommended(eElement);
        nutrient.setRecommended(recommended);
        nutrientRepository.save(nutrient);
    }

    private Recommended loadRecommended(Element eElement) {
        Recommended recommended = new Recommended();
        if (eElement.getElementsByTagName("recommended_per_kg").getLength() == 0) {
            Element e = (Element) eElement.getElementsByTagName("recommended").item(0);

            recommended.setMan(Double.valueOf(e.getElementsByTagName("man").item(0).getTextContent()));
            recommended.setWoman(Double.valueOf(e.getElementsByTagName("woman").item(0).getTextContent()));
            recommended.setPregnant(Double.valueOf(e.getElementsByTagName("pregnant").item(0).getTextContent()));
            recommended.setBreastfeeding(Double.valueOf(e.getElementsByTagName("breastfeeding").item(0).getTextContent()));
            recommended.setMax(parseDouble(e.getElementsByTagName("max").item(0).getTextContent()));
        } else {
            Element e = (Element) eElement.getElementsByTagName("recommended_per_kg").item(0);
            recommended.setRecommendedPerKg(Double.valueOf(e.getTextContent()));
        }
        recommended = recommendedRepository.save(recommended);
        return recommended;
    }

    private double parseDouble(String string) {
        try {
            return Double.parseDouble(string);
        } catch (Exception ignored) {
            return Double.POSITIVE_INFINITY;
        }
    }

    public List<Nutrient> getNutrients() {
        return nutrientRepository.findAll();
    }

    public NutrientsResponse getNutrients(NutrientsRequest nutrientsRequest) {
        Plan plan = planRepository.findById(nutrientsRequest.getPlanId()).get();
        DailyPlan dailyPlan = plan.getDailyPlans().get(nutrientsRequest.getDay() - 1);
        ClientData clientData = clientRepository.findByEmail(dailyPlan.getUserEmail()).getClientData();

        NutrientsResponse nutrientsResponse = new NutrientsResponse(getNutrients(), clientData);
        nutrientsResponse.setGoals(clientData);

        for (Meal meal : dailyPlan.getMeals()) {
            for (EatenFood food : meal.getEatenFood()) {
                FoodItem item = food.getFoodItem();
                nutrientsResponse.addMacronutrients(item, food.getQuantity());
                addFoodNutrients(nutrientsResponse.getNutrientsScore(), item, food.getQuantity());
            }
        }

        nutrientsResponse.updatePercents();
        return nutrientsResponse;
    }

    private void addFoodNutrients(List<NutrientScore> nutrients, FoodItem foodItem, Double amount) {
        for (NutrientScore score : nutrients) {
            for (NutrientQuantity n : foodItem.getNutrients()) {
                if (score.getNutrient().getSymbol().equals(n.getNutrient())) {
                    score.addAmount(getNutrientQuantity(foodItem, n.getNutrient()) * amount / 100);
                }
            }
        }
    }

    private Double getNutrientQuantity(FoodItem foodItem, String nutrientSymbol) {
        for (NutrientQuantity nutrientQuantity : foodItem.getNutrients()) {
            if (nutrientQuantity.getNutrient().equals(nutrientSymbol)) {
                return nutrientQuantity.getQuantity();
            }
        }
        System.out.println("Nutrient not found: " + nutrientSymbol);
        return 0.0;
    }

}
