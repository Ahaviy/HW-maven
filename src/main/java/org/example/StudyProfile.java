package org.example;

public enum StudyProfile {
    MEDICINE ("Медицина"), IT ("Информатика"), PEDAGOGICAL("Педагогика"),
    CHEMISTRY("Химия"), UNKNOWN("Не указано"), TELECOMMUNICATION("Телекомуникации");

    private String rusName;

    public String getRusName() {
        return rusName;
    }

    StudyProfile(String rusName) {
        this.rusName = rusName;
    }
}
