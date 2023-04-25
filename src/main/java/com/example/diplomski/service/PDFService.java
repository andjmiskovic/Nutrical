package com.example.diplomski.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

public class PDFService {

    public static void createPDF() {
        Document document = new Document(PageSize.A4);
        try {
            PdfWriter.getInstance(document, new FileOutputStream("NutritionPlan.pdf"));
            document.open();

            // Add a logo
            Image logo = Image.getInstance("src/main/resources/logo.png");
            logo.setAlignment(Image.ALIGN_CENTER);
            document.add(logo);

            // Add a title
            Paragraph title = new Paragraph("7-Day Nutrition Plan");
            document.add(title);

            // Create a table with 5 columns (one for each meal of the day) and 7 rows (one for each day of the week)
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);

            // Add column headers
            table.addCell(new PdfPCell(new Paragraph("Day")));
            table.addCell(new PdfPCell(new Paragraph("Breakfast")));
            table.addCell(new PdfPCell(new Paragraph("Snack")));
            table.addCell(new PdfPCell(new Paragraph("Lunch")));
            table.addCell(new PdfPCell(new Paragraph("Dinner")));

            // Add data to the table
            for (int i = 1; i <= 7; i++) {
                table.addCell(new PdfPCell(new Paragraph("Day " + i)));
                table.addCell(new PdfPCell(new Paragraph("Oatmeal with fruit")));
                table.addCell(new PdfPCell(new Paragraph("Yogurt with nuts")));
                table.addCell(new PdfPCell(new Paragraph("Grilled chicken with vegetables")));
                table.addCell(new PdfPCell(new Paragraph("Salmon with brown rice and broccoli")));
            }
            document.add(table);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }
}
