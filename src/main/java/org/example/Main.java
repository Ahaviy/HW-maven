package org.example;

import java.util.ArrayList;

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
        for (University univer : universities) {
            System.out.println(univer);
            System.out.println("");
        }
        System.out.println("Список студентов: ");
        System.out.println();
        for (Student stud : students) {
            System.out.println(stud);
            System.out.println("");
        }
    }
}