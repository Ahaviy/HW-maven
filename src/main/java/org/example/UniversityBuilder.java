package org.example;

public class UniversityBuilder {
    private String id;
    private String fullName;
    private String shortName;
    private int yearOfFoundation;
    private StudyProfile mainProfile;

    public UniversityBuilder(String id, String fullName) {
        this.id = id;
        this.fullName = fullName;
        shortName = "не указанно";
        yearOfFoundation =0;
        mainProfile=StudyProfile.UNKNOWN;
    }

    public UniversityBuilder setShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public UniversityBuilder setYearOfFoundation(int yearOfFoundation) {
        this.yearOfFoundation = yearOfFoundation;
        return this;
    }

    public UniversityBuilder setMainProfile(StudyProfile mainProfile) {
        this.mainProfile = mainProfile;
        return this;
    }

    public University createUniversity() {
        return new University(id, fullName, shortName, yearOfFoundation, mainProfile);
    }
}