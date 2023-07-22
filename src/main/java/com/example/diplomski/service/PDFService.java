package com.example.diplomski.service;

import com.example.diplomski.model.DailyPlan;
import com.example.diplomski.model.EatenFood;
import com.example.diplomski.model.Plan;
import com.example.diplomski.model.Tag;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.*;

public class PDFService {

    private static final String FILE_LOCATION = "src/main/resources/report.pdf";

    public static ByteArrayInputStream createPDF(Plan plan) throws IOException {
        Document document = new Document(PageSize.A4);
        FileOutputStream output = new FileOutputStream(FILE_LOCATION);
        try {
            PdfWriter.getInstance(document, output);
            document.open();

            Paragraph title = new Paragraph("Nutrition Plan");
            document.add(title);

            PdfPTable table = createTable(plan);
            document.add(table);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
        }

        return renderPdf();
    }

    private static ByteArrayInputStream renderPdf() throws IOException {
        File readFile = new File(FILE_LOCATION);
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

    private static PdfPTable createTable(Plan plan) {
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setPaddingTop(5);

        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
        Font cellFont = FontFactory.getFont(FontFactory.HELVETICA, 10);

        int i = 1;
        for (DailyPlan dailyPlan : plan.getDailyPlans()) {
            // Add a single row for the day
            PdfPCell dayCell = new PdfPCell(new Phrase("Day " + i, headerFont));
            dayCell.setColspan(3);
            dayCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            table.addCell(dayCell);

            // Add multiple rows for each meal of the day
            for (Tag tag : dailyPlan.getTags()) {
                PdfPCell mealCell = new PdfPCell(new Phrase(tag.getTag(), cellFont));
                mealCell.setRowspan(tag.getEatenFood().size());
                table.addCell(mealCell);

                for (EatenFood food : tag.getEatenFood()) {
                    PdfPCell foodNameCell = new PdfPCell(new Phrase(food.getFoodItem().getName(), cellFont));
                    table.addCell(foodNameCell);

                    PdfPCell quantityCell = new PdfPCell(new Phrase(food.getQuantity().toString() + "g", cellFont));
                    table.addCell(quantityCell);
                }
            }
            i++;
        }
        return table;
    }
}
