package com.example.diplomski.service;

import com.example.diplomski.dto.NutrientsRequest;
import com.example.diplomski.dto.NutrientsResponse;
import com.example.diplomski.enums.HealthStatus;
import com.example.diplomski.enums.NutrientKind;
import com.example.diplomski.model.*;
import com.example.diplomski.repository.*;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
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

    public NutrientsResponse getNutrients(NutrientsRequest nutrientsRequest) {
        NutrientsResponse nutrientsResponse = new NutrientsResponse();

        Plan plan = planRepository.findById(nutrientsRequest.getPlanId()).get();
        DailyPlan dailyPlan = plan.getDailyPlans().get(nutrientsRequest.getDay() - 1);
        ClientData clientData = clientRepository.findByEmail(dailyPlan.getUserEmail()).getClientData();
        nutrientsResponse.setCaloriesGoal(calculateCalories(clientData));
        nutrientsResponse.setNutrientsGoals(setNutrientsGoal(clientData.getHealthStatus()));
        nutrientsResponse.setNutrients(createEmptyNutrientMap());

        for (Tag tag : dailyPlan.getTags()) {
            for (EatenFood food : tag.getEatenFood()) {
                FoodItem item = food.getFoodItem();
                nutrientsResponse.addCalories(item.getCalories() * food.getQuantity() / 100);
                addFoodNutrients(nutrientsResponse.getNutrients(), item, food.getQuantity());
            }
        }
        return nutrientsResponse;
    }

    private HashMap<Nutrient, Double> setNutrientsGoal(HealthStatus healthStatus) {
        HashMap<Nutrient, Double> nutrients = new HashMap<>();
        for (Nutrient nutrient : getNutrients()) {
            nutrients.put(nutrient, nutrient.getRecommended().getValue(healthStatus));
        }
        return nutrients;
    }

    private void addFoodNutrients(HashMap<Nutrient, Double> nutrients, FoodItem foodItem, Double amount) {
        nutrients.replaceAll((nutrient, value) -> nutrients.get(nutrient) + getNutrientQuantity(foodItem, nutrient) * amount / 100);
    }

    private Double getNutrientQuantity(FoodItem foodItem, Nutrient nutrient) {
        for (NutrientQuantity nutrientQuantity : foodItem.getNutrients()) {
            System.out.println(nutrientQuantity.getNutrient() + " : " + nutrient.getSymbol());
            if (nutrientQuantity.getNutrient().equals(nutrient.getSymbol())) {
                return nutrientQuantity.getQuantity();
            }
        }
        System.out.println("Nutrient not found: " + nutrient.getSymbol());
        return 0.0;
    }

    public double calculateBMI(double weight, double height) {
        return weight / (height * height / 10000);
    }

    public double calculateCalories(ClientData clientData) {
        int age = calculateAge(clientData.getDateOfBirth());
        double base = 9.99 * clientData.getWeight() + 6.25 * clientData.getHeight() - 4.92 * age;
        switch (clientData.getHealthStatus()) {
            case MAN -> base += 5;
            case WOMAN -> base -= 161;
            case PREGNANT -> base += 340;
            default -> base += 400;
        }
        return base * clientData.getActivityStatus().getMultiplyValue();
    }

    private int calculateAge(Date dateOfBirth) {
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        int d1 = Integer.parseInt(formatter.format(dateOfBirth));
        int d2 = Integer.parseInt(formatter.format(new Date()));
        return (d2 - d1) / 10000;
    }
}
