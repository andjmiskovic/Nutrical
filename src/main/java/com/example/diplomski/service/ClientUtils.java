package com.example.diplomski.service;

import com.example.diplomski.model.ClientData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientUtils {
    public static Double calculateBMI(ClientData clientData) {
        return roundAvoid(clientData.getWeight() / (clientData.getHeight() * clientData.getHeight() / 10000), 1);
    }

    public static int calculateAge(Date dateOfBirth) {
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        int d1 = Integer.parseInt(formatter.format(dateOfBirth));
        int d2 = Integer.parseInt(formatter.format(new Date()));
        return (d2 - d1) / 10000;
    }

    public static double calculateCalories(ClientData clientData) {
        int age = calculateAge(clientData.getDateOfBirth());
        double base = 9.99 * clientData.getWeight() + 6.25 * clientData.getHeight() - 4.92 * age;
        switch (clientData.getHealthStatus()) {
            case MAN -> base += 5;
            case WOMAN -> base -= 161;
            case PREGNANT -> base += 340;
            default -> base += 400;
        }
        return roundAvoid(base * clientData.getActivityStatus().getMultiplyValue(), 0);
    }

    public static double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }
}
