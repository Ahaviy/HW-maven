package org.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

public class StatisticGenerator {
    private static final Logger logger = Logger.getLogger(StatisticGenerator.class.getName());

    public static Collection<Statistics> createStatistics(Collection<Student> students, Collection<University> universities) {
        ArrayList<Statistics> list = new ArrayList<>();
        logger.fine("Групируем университеты по профилю");
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
        logger.fine("Для каждого профиля подсчитываем студенитов");
        students.stream().forEach(student -> {
            StudyProfile profile = (universities.stream().filter(p -> p.getId().equals(student.getUniversityId()))
                    .findFirst().orElse(null)).getMainProfile();
            Statistics stat = list.get(list.indexOf(new Statistics(profile)));
            stat.setStudentNumber(stat.getStudentNumber() + 1);
            stat.addaverageScore(student.getAvgExamScore());

        });
        logger.info("статистика успешна сгенерирована");
        return list;

    }
}
