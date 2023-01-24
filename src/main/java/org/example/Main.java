package org.example;

import java.util.ArrayList;

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
        for (University univer : universities) {
            System.out.println(univer);
            System.out.println("");
        }
        System.out.println("������ ���������: ");
        System.out.println();
        for (Student stud : students) {
            System.out.println(stud);
            System.out.println("");
        }
    }
}