package org.example;

import org.example.comparators.Comparators;
import org.example.comparators.CompareStudent;
import org.example.comparators.CompareUniversity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static final Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {

        logger.addHandler(new ConsoleHandler());
        System.out.println(logger.getHandlers().length);


        logger.log(Level.WARNING,"Warning Test");
        logger.log(Level.SEVERE,"SEVERE Test");
        logger.log(Level.INFO,"INFO test");
        logger.log(Level.FINE,"FINE Test");





        UniversityInfoReader uiReader = UniversityInfoReader.getUIReader();
        uiReader.setFilePath("src/main/resources/universityInfo.xlsx");
        logger.log(Level.INFO,"Читаем из файлов ресурсов список университетов.");
        ArrayList<University> universities = uiReader.getUniversities();
        if (universities != null) {
            logger.log(Level.INFO,"Список университетов успешно прочитан.");
        } else {
            logger.log(Level.WARNING,"Не удалось прочитать список университетов.");
        }
        logger.log(Level.INFO,"Читаем из файлов ресурсов список студентов.");
        ArrayList<Student> students = uiReader.getStudents();
        if (students != null) {
            System.out.println();
            logger.log(Level.INFO,"Список студентов успешно прочитан.");
        } else {
            logger.log(Level.WARNING,"Не удалось прочитать список студентов.");
        }

        new XLSWriter().writeXLS(StatisticGenerator.createStatistics(students,universities), "test.xlsx");

    }
}