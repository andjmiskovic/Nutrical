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
        clientInfo.add(new Chunk("Client Data\n\n"));
        clientInfo.add(new Chunk("Name: " + client.getName() + " " + client.getSurname() + "\n"));
        clientInfo.add(new Chunk("Email: " + client.getEmail() + "\n"));
        clientInfo.add(new Chunk("Date of birth: " + client.getClientData().getDateOfBirth() + "\n"));
        clientInfo.add(new Chunk("Age: " + calculateAge(client.getClientData().getDateOfBirth()) + "\n"));
        clientInfo.add(new Chunk("Biological status: " + client.getClientData().getBiologicalStatus() + "\n"));
        clientInfo.add(new Chunk("Activity status: " + client.getClientData().getActivityStatus() + "\n"));
        clientInfo.add(new Chunk("BMI: " + calculateBMI(client.getClientData()) + "\n"));
        clientInfo.add(new Chunk("Maintenance Calories: " + calculateCalories(client.getClientData()) + "\n"));
        clientInfo.add(new Chunk("Daily calorie goal: " + client.getClientData().getCalorieGoal() + "\n"));
        clientInfo.add(new Chunk("Macronutrient ratio: C(" + client.getClientData().getCarbsPercent() + "%) P(" + client.getClientData().getProteinPercent() + "%) F(" + client.getClientData().getFatPercent() + "%)\n"));
        if (!client.getClientData().getAdditionalInformation().trim().isEmpty()) {
            clientInfo.add(new Chunk("Additional information: \n"));
            clientInfo.add(new Chunk(client.getClientData().getAdditionalInformation()));
        }
        return clientInfo;
    }

    private static PdfPTable createTable(Plan plan) {
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setPaddingTop(5);

        int i = 1;
        for (DailyPlan dailyPlan : plan.getDailyPlans()) {
            // Add a single row for the day
            PdfPCell dayCell = new PdfPCell(new Phrase("Day " + i, HEADER_FONT));
            dayCell.setColspan(3);
            dayCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            table.addCell(dayCell);

            // Add multiple rows for each meal of the day
            for (Meal meal : dailyPlan.getMeals()) {
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

            addNotes(table, dailyPlan.getTraining(), "Training");
            addNotes(table, dailyPlan.getNotes(), "Notes");
            i++;
        }
        return table;
    }

    private static void addNotes(PdfPTable table, String value, String header) {
        if (!(value == null || !value.trim().isEmpty())) {
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
