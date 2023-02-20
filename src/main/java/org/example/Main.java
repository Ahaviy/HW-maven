package org.example;

import org.example.comparators.Comparators;
import org.example.comparators.CompareStudent;
import org.example.comparators.CompareUniversity;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {

    private static final String LOGGINGPROPERTIES = "src/main/resources/logging.properties";

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream(LOGGINGPROPERTIES));
        } catch (IOException e) {
            System.out.println("Не получилось загрузаить файл конфигурации: " + LOGGINGPROPERTIES);
            System.out.println("причина: " + e.getMessage());
            System.out.println("Вывод будет только в консоль");
        }

        UniversityInfoReader uiReader = UniversityInfoReader.getUIReader();
        uiReader.setFilePath("src/main/resources/universityInfo.xlsx");
        logger.log(Level.INFO, "Читаем из файлов ресурсов список университетов.");
        ArrayList<University> universities = uiReader.getUniversities();
        if (universities != null) {
            logger.log(Level.INFO, "Список университетов успешно прочитан.");
        } else {
            logger.log(Level.WARNING, "Не удалось прочитать список университетов.");
        }
        logger.log(Level.INFO, "Читаем из файлов ресурсов список студентов.");
        ArrayList<Student> students = uiReader.getStudents();
        if (students != null) {
            System.out.println();
            logger.log(Level.INFO, "Список студентов успешно прочитан.");
        } else {
            logger.log(Level.WARNING, "Не удалось прочитать список студентов.");
        }

        logger.log(Level.INFO, "Собираем статистику и сохраняем в файл");

        new XLSWriter().writeXLS(StatisticGenerator.createStatistics(students, universities), "test.xlsx");

    }
}