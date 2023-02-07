package org.example;

import java.util.ArrayList;
import java.util.Collection;

public class StatisticGenerator {

    public static Collection<Statistics> createStatistics(Collection<Student> students, Collection<University> universities) {
        ArrayList<Statistics> list = new ArrayList<>();
        universities.stream().forEach(university -> {
            Statistics stat = new Statistics(university.getMainProfile());
            if (list.contains(stat)) {
                stat = list.get(list.indexOf(stat));
            } else {
                list.add(stat);
            }
            stat.setUniversityNumber(stat.getUniversityNumber() + 1);
            stat.getUniversityNames().add(university.getFullName());
        });
        students.stream().forEach(student -> {
            StudyProfile profile = (universities.stream().filter(p -> p.getId().equals(student.getUniversityId()))
                    .findFirst().orElse(null)).getMainProfile();
            Statistics stat = list.get(list.indexOf(new Statistics(profile)));
            stat.setStudentNumber(stat.getStudentNumber() + 1);
            stat.addaverageScore(student.getAvgExamScore());

        });


        return list;

    }
}
