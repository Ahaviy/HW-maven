package org.example;

public class StudentBuilder {
    private String fullName;
    private String universityId;
    private int currentCourseNumber;
    private float avgExamScore;

    public StudentBuilder(String fullName) {
        this.fullName = fullName;
        universityId = "нет данных";
        currentCourseNumber = 0;
        avgExamScore = 0;
    }

    public StudentBuilder setUniversityId(String universityId) {
        this.universityId = universityId;
        return this;
    }

    public StudentBuilder setCurrentCourseNumber(int currentCourseNumber) {
        this.currentCourseNumber = currentCourseNumber;
        return this;
    }

    public StudentBuilder setAvgExamScore(float avgExamScore) {
        this.avgExamScore = avgExamScore;
        return this;
    }

    public Student createStudent() {
        return new Student(fullName, universityId, currentCourseNumber, avgExamScore);
    }
}