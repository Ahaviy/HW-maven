package org.example;

public class Student {
    String fullName;
    String universityId;
    int currentCourseNumber;
    float avgExamScore;

    public Student(String fullName, String universityId, int currentCourseNumber, float avgExamScore) {
        this.fullName = fullName;
        this.universityId = universityId;
        this.currentCourseNumber = currentCourseNumber;
        this.avgExamScore = avgExamScore;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUniversityId() {
        return universityId;
    }

    public int getCurrentCourseNumber() {
        return currentCourseNumber;
    }

    public float getAvgExamScore() {
        return avgExamScore;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setUniversityId(String universityId) {
        this.universityId = universityId;
    }

    public void setCurrentCourseNumber(int currentCourseNumber) {
        this.currentCourseNumber = currentCourseNumber;
    }

    public void setAvgExamScore(float avgExamScore) {
        this.avgExamScore = avgExamScore;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ФИО: ").append(fullName).append("\n");
        sb.append("id Университета: ").append(universityId).append("\n");
        sb.append("Курс: ").append(currentCourseNumber == 0 ? "не указанно" : Integer.toString(currentCourseNumber)).append("\n");
        sb.append("Средний бал: ").append(avgExamScore == 0 ? "не указанно" : Float.toString(avgExamScore)).append("\n");
        return sb.toString().trim();
    }
}
