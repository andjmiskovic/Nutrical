package com.example.diplomski.enums;

public enum ActivityStatus {
    SEDENTARY("Sedentary", 1.2, "Little or no exercise, desk job"),
    LIGHTLY_ACTIVE("Lightly active", 1.375, "Light exercise/sports 1-3 days per week"),
    MODERATELY_ACTIVE("Moderately active", 1.55, "Moderate exercise/sports 3-5 days per week"),
    VERY_ACTIVE("Very Active", 1.725, "Hard exercise/sports 6-7 days per week"),
    EXTREMELY_ACTIVE("Extremely active", 1.9, "Hard daily exercise/sports and physical job");

    private final String typeName;
    private final double multiplyValue;
    private final String description;

    ActivityStatus(String typeName, double multiplyValue, String description) {
        this.typeName = typeName;
        this.multiplyValue = multiplyValue;
        this.description = description;
    }

    public String getTypeName() {
        return typeName;
    }

    public double getMultiplyValue() {
        return multiplyValue;
    }

    public String getDescription() {
        return description;
    }
}
