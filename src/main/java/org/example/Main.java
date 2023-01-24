package org.example;

import org.example.comparators.Comparators;
import org.example.comparators.CompareStudent;
import org.example.comparators.CompareUniversity;

import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        UniversityInfoReader uiReader = UniversityInfoReader.getUIReader();
        uiReader.setFilePath("src/main/resources/universityInfo.xlsx");
        System.out.println("Читаем из файлов ресурсов список университетов:");
        ArrayList<University> universities = uiReader.getUniversities();
        if (universities != null) {
            System.out.println("Список университетов успешно прочитан.");
        } else {
            System.out.println("не удалось прочитать список университетов");
        }
        System.out.println("Читаем из файлов ресурсов список студентов:");
        ArrayList<Student> students = uiReader.getStudents();
        if (students != null) {
            System.out.println("Список студентов успешно прочитан.");
        } else {
            System.out.println("не удалось прочитать список студентов");
        }
        System.out.println();
        System.out.println("Проверка корректности прочитанных данных:");
        System.out.println();
        System.out.println("Список университетов: ");
        universities.stream().sorted(Comparators.getUniversityComporator(CompareUniversity.byFullName)).forEach(System.out::println);
        System.out.println("Список студентов: ");
        students.stream().sorted(Comparators.getStudentComparator(CompareStudent.UniversityId)).forEach(System.out::println);

    }
}