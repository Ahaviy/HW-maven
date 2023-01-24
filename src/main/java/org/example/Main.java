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
        System.out.println("������ �� ������ �������� ������ �������������:");
        ArrayList<University> universities = uiReader.getUniversities();
        if (universities != null) {
            System.out.println("������ ������������� ������� ��������.");
        } else {
            System.out.println("�� ������� ��������� ������ �������������");
        }
        System.out.println("������ �� ������ �������� ������ ���������:");
        ArrayList<Student> students = uiReader.getStudents();
        if (students != null) {
            System.out.println("������ ��������� ������� ��������.");
        } else {
            System.out.println("�� ������� ��������� ������ ���������");
        }
        System.out.println();
        System.out.println("�������� ������������ ����������� ������:");
        System.out.println();
        System.out.println("������ �������������: ");
        universities.stream().sorted(Comparators.getUniversityComporator(CompareUniversity.byFullName)).forEach(System.out::println);
        System.out.println("������ ���������: ");
        students.stream().sorted(Comparators.getStudentComparator(CompareStudent.UniversityId)).forEach(System.out::println);

    }
}