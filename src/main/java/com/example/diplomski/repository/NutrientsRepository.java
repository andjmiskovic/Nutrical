package com.example.diplomski.repository;

import com.example.diplomski.model.Nutrient;
import com.example.diplomski.model.NutrientKind;
import com.example.diplomski.model.Recommended;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class NutrientsRepository {

    public static ArrayList<Nutrient> getNutrients() {
        ArrayList<Nutrient> nutrients = new ArrayList<>();
        try
        {
            File file = new File("src/main/resources/static/nutrients.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("nutrient");
            for (int itr = 0; itr < nodeList.getLength(); itr++)
            {
                Node node = nodeList.item(itr);
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
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
                    nutrient.setRecommended(recommended);

                    nutrients.add(nutrient);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return nutrients;
    }
}
