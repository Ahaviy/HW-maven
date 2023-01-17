package org.example;

public enum StudyProfile {
    MEDICINE ("Медицина"), PHYSICS ("Физика"), LINGUISTICS ("Языковединие"),
    MATHEMATICS("Математика"), UNKNOWN("Не указано");

    private String rusName;

    public String getRusName() {
        return rusName;
    }

    StudyProfile(String rusName) {
        this.rusName = rusName;
    }
}
