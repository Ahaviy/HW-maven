package org.example;

public enum StudyProfile {
    MEDICINE ("��������"), PHYSICS ("������"), LINGUISTICS ("������������"),
    MATHEMATICS("����������"), UNKNOWN("�� �������");

    private String rusName;

    public String getRusName() {
        return rusName;
    }

    StudyProfile(String rusName) {
        this.rusName = rusName;
    }

}
