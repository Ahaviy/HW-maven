package org.example;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UniversityInfoReader {

    private static final Logger logger = Logger.getLogger(UniversityInfoReader.class.getName());

    // �������� ������� � ����� ��������
    private final String UNIVERSITIESSHEETNAME = "������������";
    private final String STUDENTSSHEETNAME = "��������";

    private String filePath;

    private static UniversityInfoReader uiReader;

    public static UniversityInfoReader getUIReader() {
        if (uiReader == null) {
            uiReader = new UniversityInfoReader();
        }
        return uiReader;
    }


    private UniversityInfoReader() {
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;

    }

    /**
     * ������ ���� ��������, �������� ������������� � ���������� ������ (ArrayList) �������������
     *
     * @return ������ ������������� ���� ������� ��������� ��� null ���� �� �������
     */
    public ArrayList<University> getUniversities() {
        ArrayList<University> universities = new ArrayList<>();
        University university;
        //�������� ������� ����� ��������
        if (filePath == null || !(new File(filePath)).exists()) {
            logger.log(Level.WARNING, "���������� ����� ���� ������: " + filePath);
            logger.log(Level.WARNING, "���������� ������ �� ����� �� ��������!");
            return null;
        }
        // �������� ������� ����� � �������������� � �����
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            XSSFWorkbook xssfw = (new XSSFWorkbookFactory()).create(fileInputStream);
            if (xssfw.getSheetIndex(UNIVERSITIESSHEETNAME) == -1) {
                logger.log(Level.WARNING, "� �����: " + filePath + " ���������� ����: " + UNIVERSITIESSHEETNAME);
                logger.log(Level.WARNING, "���������� ������ �� ����� �� ��������!");
                return null;
            }
            Iterator<Row> iterator = xssfw.getSheet(UNIVERSITIESSHEETNAME).iterator(); // �������� ��������
            iterator.next(); // ���������� ������ � ��������� ��������
            while (iterator.hasNext()) {
                Row row = iterator.next();
                //���������� ������� ������������ �� ������ 5-�� �������
                StudyProfile studyProfile = switch (row.getCell(4).toString()) {
                    case "PHYSICS" -> StudyProfile.PHYSICS;
                    case "MEDICINE" -> StudyProfile.MEDICINE;
                    case "LINGUISTICS" -> StudyProfile.LINGUISTICS;
                    case "MATHEMATICS" -> StudyProfile.MATHEMATICS;
                    default -> StudyProfile.UNKNOWN;
                };
                university = new UniversityBuilder(row.getCell(0).toString(), row.getCell(1).toString())
                        .setShortName(row.getCell(2).toString())
                        .setYearOfFoundation(Integer.parseInt(row.getCell(3).toString().substring(0, 4)))
                        .setMainProfile(studyProfile)
                        .createUniversity();
                universities.add(university);
                logger.log(Level.FINE, "������� �������� � �������� � ������ �����������: " + university.getId());
            }
            xssfw.close();
        } catch (IOException e) {
            logger.log(Level.WARNING, "�������� ������ ��� ������ �����: " + filePath);
            logger.log(Level.WARNING, e.getMessage());
            return null;
        }
        if (universities.size() > 0) {
            logger.log(Level.INFO, "������� �������� ����. ���������� �������������: " + Integer.toString(universities.size()));
            return universities;
        }
        return null;
    }

    /**
     * ������ ���� ��������, �������� ��������� � ���������� ������ (ArrayList) ���������
     *
     * @return ������ ��������� ���� ������� ��������� ��� null ���� �� �������
     */
    public ArrayList<Student> getStudents() {
        ArrayList<Student> students = new ArrayList<>();
        Student student;
        //�������� ������� ����� ��������
        if (filePath == null || !(new File(filePath)).exists()) {
            logger.log(Level.WARNING, "���������� ����� ���� ������: " + filePath);
            logger.log(Level.WARNING, "���������� ������ �� ����� �� ��������!");
            return null;
        }
        // �������� ������� ����� �� ���������� � �����
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            XSSFWorkbook xssfw = (new XSSFWorkbookFactory()).create(fileInputStream);
            if (xssfw.getSheetIndex(STUDENTSSHEETNAME) == -1) {
                logger.log(Level.WARNING, "� �����: " + filePath + " ���������� ����: " + STUDENTSSHEETNAME);
                logger.log(Level.WARNING, "���������� ������ �� ����� �� ��������!");
                return null;
            }
            Iterator<Row> iterator = xssfw.getSheet(STUDENTSSHEETNAME).iterator(); // �������� ��������
            iterator.next(); // ���������� ������ � ��������� ��������
            while (iterator.hasNext()) {
                Row row = iterator.next();
                student = new StudentBuilder(row.getCell(1).toString())
                        .setUniversityId(row.getCell(0).toString())
                        .setCurrentCourseNumber(Integer.parseInt(row.getCell(2).toString().substring(0, 1)))
                        .setAvgExamScore(Float.parseFloat(row.getCell(3).toString()))
                        .createStudent();
                students.add(student);
                logger.log(Level.FINE, "������� �������� � �������� � ������ �����������: " + student.getFullName());
            }
            xssfw.close();
        } catch (IOException e) {
            logger.log(Level.WARNING, "�������� ������ ��� ������ �����: " + filePath);
            logger.log(Level.WARNING, e.getMessage());
            return null;
        }
        if (students.size() > 0) {
            logger.log(Level.INFO, "������� �������� ����. ���������� ���������: " + Integer.toString(students.size()));

            return students;
        }
        return null;
    }


}
