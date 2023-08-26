package com.example.diplomski.service;

import com.example.diplomski.model.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.*;

import static com.example.diplomski.util.ClientUtils.*;

public class PDFService {

    private static final Font HEADER_FONT = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
    private static final Font CELL_FONT = FontFactory.getFont(FontFactory.HELVETICA, 10);

    public static ByteArrayInputStream createPDF(Plan plan) throws IOException {
        Document document = new Document(PageSize.A4);
        FileOutputStream output = new FileOutputStream(getFileLocation(plan.getId()));
        try {
            PdfWriter.getInstance(document, output);
            document.open();

            document.add(new Paragraph("Nutrition Plan"));
            document.add(createClientInfo(plan.getClient()));
            document.add(createTable(plan));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
        }

        return renderPdf(plan.getId());
    }

    private static ByteArrayInputStream renderPdf(Long planId) throws IOException {
        File readFile = new File(getFileLocation(planId));
        FileInputStream fileInputStream = new FileInputStream(readFile);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, bytesRead);
        }
        fileInputStream.close();

        byte[] pdfBytes = byteArrayOutputStream.toByteArray();
        return new ByteArrayInputStream(pdfBytes);
    }

    private static Paragraph createClientInfo(Client client) {
        Paragraph clientInfo = new Paragraph();

        clientInfo.setPaddingTop(10f);
        Font labelFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.BLACK);
        Font valueFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.DARK_GRAY);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);

        addClientDetailRow(table, "Name:", client.getName() + " " + client.getSurname(), labelFont, valueFont);
        addClientDetailRow(table, "Email:", client.getEmail(), labelFont, valueFont);
        addClientDetailRow(table, "Date of birth:", client.getClientData().getDateOfBirth().toString(), labelFont, valueFont);
        addClientDetailRow(table, "Age:", String.valueOf(calculateAge(client.getClientData().getDateOfBirth())), labelFont, valueFont);
        addClientDetailRow(table, "Biological status:", client.getClientData().getBiologicalStatus().getName(), labelFont, valueFont);
        addClientDetailRow(table, "Activity status:", client.getClientData().getActivityStatus().getDescription(), labelFont, valueFont);
        addClientDetailRow(table, "BMI:", String.valueOf(calculateBMI(client.getClientData())), labelFont, valueFont);
        addClientDetailRow(table, "Maintenance Calories:", String.valueOf(calculateCalories(client.getClientData())), labelFont, valueFont);
        addClientDetailRow(table, "Daily calorie goal:", String.valueOf(client.getClientData().getCalorieGoal()), labelFont, valueFont);
        addClientDetailRow(table, "Macronutrient ratio:",
                "C(" + client.getClientData().getCarbsPercent() + "%) P(" + client.getClientData().getProteinPercent() + "%) F(" + client.getClientData().getFatPercent() + "%)",
                labelFont, valueFont);

        if (!client.getClientData().getAdditionalInformation().trim().isEmpty()) {
            PdfPCell additionalInfoCell = new PdfPCell(new Phrase("Additional information:", labelFont));
            additionalInfoCell.setBorder(Rectangle.NO_BORDER);
            additionalInfoCell.setVerticalAlignment(Element.ALIGN_TOP);
            table.addCell(additionalInfoCell);

            PdfPCell additionalInfoValueCell = new PdfPCell(new Phrase(client.getClientData().getAdditionalInformation(), valueFont));
            additionalInfoValueCell.setBorder(Rectangle.NO_BORDER);
            additionalInfoValueCell.setVerticalAlignment(Element.ALIGN_TOP);
            table.addCell(additionalInfoValueCell);
        }

        clientInfo.add(table);
        return clientInfo;
    }

    private static void addClientDetailRow(PdfPTable table, String label, String value, Font labelFont, Font valueFont) {
        PdfPCell labelCell = new PdfPCell(new Phrase(label, labelFont));
        labelCell.setBorder(Rectangle.NO_BORDER);
        table.addCell(labelCell);

        PdfPCell valueCell = new PdfPCell(new Phrase(value, valueFont));
        valueCell.setBorder(Rectangle.NO_BORDER);
        table.addCell(valueCell);
    }

    private static PdfPTable createTable(Plan plan) {
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setPaddingTop(10);

        int i = 1;
        for (DailyPlan dailyPlan : plan.getDailyPlans()) {
            if (dailyPlan.getMeals().size() > 0) {
                // Add a single row for the day
                PdfPCell dayCell = new PdfPCell(new Phrase("Day " + i, HEADER_FONT));
                dayCell.setColspan(3);
                dayCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                table.addCell(dayCell);

                // Add multiple rows for each meal of the day
                for (Meal meal : dailyPlan.getMeals()) {
                    if (meal.getEatenFood().size() > 0) {
                        PdfPCell mealCell = new PdfPCell(new Phrase(meal.getName(), CELL_FONT));
                        mealCell.setRowspan(meal.getEatenFood().size());
                        table.addCell(mealCell);

                        for (EatenFood food : meal.getEatenFood()) {
                            PdfPCell foodNameCell = new PdfPCell(new Phrase(food.getFoodItem().getName(), CELL_FONT));
                            table.addCell(foodNameCell);

                            PdfPCell quantityCell = new PdfPCell(new Phrase(food.getQuantity().toString() + "g", CELL_FONT));
                            table.addCell(quantityCell);
                        }
                    }
                }

                addNotes(table, dailyPlan.getTraining(), "Training");
                addNotes(table, dailyPlan.getNotes(), "Notes");
                i++;
            }
        }
        return table;
    }

    private static void addNotes(PdfPTable table, String value, String header) {
        if (value != null && !value.isBlank()) {
            PdfPCell notes = new PdfPCell(new Phrase(header, CELL_FONT));
            table.addCell(notes);
            PdfPCell notesContent = new PdfPCell(new Phrase(value, CELL_FONT));
            notesContent.setColspan(2);
            table.addCell(notesContent);
        }
    }

    public static String getFileLocation(Long planId) {
        return "src/main/resources/report" + planId + ".pdf";
    }
}
