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

public class UniversityInfoReader {

    // Названия страниц в файле ресурсов
    private final String UNIVERSITIESSHEETNAME = "Университеты";
    private final String STUDENTSSHEETNAME = "Студенты";

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
     * Читает файл ресурсов, страницу университетов и возвращает список (ArrayList) университетов
     * @return список университетов если удалось прочитать или null если не удалось
     */
    public ArrayList<University> getUniversities() {
        ArrayList<University> universities = new ArrayList<>();
        University university;
        //Проверка наличия файла ресурсов
        if (filePath == null || !(new File(filePath)).exists()) {
            System.out.println("Невозможно найти файл данных");
            System.out.println("Дальнейшее чтение из файла не возможно!");
            return null;
        }
        // Проверка наличия листа с университетами в файле
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            XSSFWorkbook xssfw = (new XSSFWorkbookFactory()).create(fileInputStream);
            if (xssfw.getSheetIndex(UNIVERSITIESSHEETNAME) == -1) {
                System.out.println("В файле: " + filePath + " отсутсвует лист: " + UNIVERSITIESSHEETNAME);
                System.out.println("Дальнейшее чтение из файла не возможно!");
                return null;
            }
            Iterator<Row> iterator = xssfw.getSheet(UNIVERSITIESSHEETNAME).iterator(); // получаем итератор
            iterator.next(); // пропускаем строку с названием столбцов
            while (iterator.hasNext()) {
                Row row = iterator.next();
                //Определяем профиль университета из текста 5-го столбца
                StudyProfile studyProfile = switch (row.getCell(4).toString()) {
                    case "PHYSICS" -> StudyProfile.PHYSICS;
                    case "MEDICINE" -> StudyProfile.MEDICINE;
                    case "LINGUISTICS" -> StudyProfile.LINGUISTICS;
                    case "MATHEMATICS" -> StudyProfile.MATHEMATICS;
                    default -> StudyProfile.UNKNOWN;
                };
                university = new UniversityBuilder(row.getCell(0).toString(), row.getCell(1).toString())
                        .setShortName(row.getCell(2).toString())
                        .setYearOfFoundation(Integer.parseInt(row.getCell(3).toString().substring(0,4)))
                        .setMainProfile(studyProfile)
                        .createUniversity();
                universities.add(university);
            }
            xssfw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (universities.size() > 0) return universities;
        return null;
    }

    /**
     * Читает файл ресурсов, страницу студентов и возвращает список (ArrayList) студентов
     * @return список студентов если удалось прочитать или null если не удалось
     */
    public ArrayList<Student> getStudents() {
        ArrayList<Student> students = new ArrayList<>();
        Student student;
        //Проверка наличия файла ресурсов
        if (filePath == null || !(new File(filePath)).exists()) {
            System.out.println("Невозможно найти файл данных");
            System.out.println("Дальнейшее чтение из файла не возможно!");
            return null;
        }
        // Проверка наличия листа со студентами в файле
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            XSSFWorkbook xssfw = (new XSSFWorkbookFactory()).create(fileInputStream);
            if (xssfw.getSheetIndex(STUDENTSSHEETNAME) == -1) {
                System.out.println("В файле: " + filePath + " отсутсвует лист: " + STUDENTSSHEETNAME);
                System.out.println("Дальнейшее чтение из файла не возможно!");
                return null;
            }
            Iterator<Row> iterator = xssfw.getSheet(STUDENTSSHEETNAME).iterator(); // получаем итератор
            iterator.next(); // пропускаем строку с названием столбцов
            while (iterator.hasNext()) {
                Row row = iterator.next();
                student = new StudentBuilder(row.getCell(1).toString())
                        .setUniversityId(row.getCell(0).toString())
                        .setCurrentCourseNumber(Integer.parseInt(row.getCell(2).toString().substring(0,1)))
                        .setAvgExamScore(Float.parseFloat(row.getCell(3).toString()))
                        .createStudent();
                students.add(student);
            }
            xssfw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (students.size()>0) return students;
        return null;
    }


}
