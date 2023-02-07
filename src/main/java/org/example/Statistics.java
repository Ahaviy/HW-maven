package org.example;

import java.util.ArrayList;
import java.util.List;

public class Statistics {

    private StudyProfile profile;
    private int studentNumber;
    private int universityNumber;
    private float averageScore;
    private List<String> universityNames;

    private int scoreNumber;

    private float score;

    public Statistics(StudyProfile profile) {
        this.profile = profile;
        universityNames = new ArrayList<>();
    }

    public StudyProfile getProfile() {
        return profile;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public int getUniversityNumber() {
        return universityNumber;
    }

    public float getAverageScore() {
        if (scoreNumber != 0) averageScore = score / scoreNumber;
        return averageScore;
    }

    public List<String> getUniversityNames() {
        return universityNames;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public void setUniversityNumber(int universityNumber) {
        this.universityNumber = universityNumber;
    }

    public void addaverageScore(float avScore) {
        if (avScore !=0) {
            scoreNumber++;
            score+=avScore;
        }
    }

    @Override
    public boolean equals(Object obj) {
        return ((Statistics) obj).profile.equals(this.profile);
    }
}
