package org.example;

public class Student {
    private String fullName;
    private String universityId;
    private int currentCourseNumber;
    private float avgExamScore;

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
        sb.append("���: ").append(fullName).append("\n");
        sb.append("id ������������: ").append(universityId).append("\n");
        sb.append("����: ").append(currentCourseNumber == 0 ? "�� ��������" : Integer.toString(currentCourseNumber)).append("\n");
        sb.append("������� ���: ").append(avgExamScore == 0 ? "�� ��������" : Float.toString(avgExamScore)).append("\n");
        return sb.toString();
    }
}
