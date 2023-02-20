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
     *
     * @return список университетов если удалось прочитать или null если не удалось
     */
    public ArrayList<University> getUniversities() {
        ArrayList<University> universities = new ArrayList<>();
        University university;
        //Проверка наличия файла ресурсов
        if (filePath == null || !(new File(filePath)).exists()) {
            logger.log(Level.WARNING, "Невозможно найти файл данных: " + filePath);
            logger.log(Level.WARNING, "Дальнейшее чтение из файла не возможно!");
            return null;
        }
        // Проверка наличия листа с университетами в файле
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            XSSFWorkbook xssfw = (new XSSFWorkbookFactory()).create(fileInputStream);
            if (xssfw.getSheetIndex(UNIVERSITIESSHEETNAME) == -1) {
                logger.log(Level.WARNING, "В файле: " + filePath + " отсутсвует лист: " + UNIVERSITIESSHEETNAME);
                logger.log(Level.WARNING, "Дальнейшее чтение из файла не возможно!");
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
                        .setYearOfFoundation(Integer.parseInt(row.getCell(3).toString().substring(0, 4)))
                        .setMainProfile(studyProfile)
                        .createUniversity();
                universities.add(university);
                logger.log(Level.FINE, "Успешно прочитан и добавлен в список университет: " + university.getId());
            }
            xssfw.close();
        } catch (IOException e) {
            logger.log(Level.WARNING, "Возникла ошибка при чтении файла: " + filePath);
            logger.log(Level.WARNING, e.getMessage());
            return null;
        }
        if (universities.size() > 0) {
            logger.log(Level.INFO, "Успешно прочитан файл. Распознано университетов: " + Integer.toString(universities.size()));
            return universities;
        }
        return null;
    }

    /**
     * Читает файл ресурсов, страницу студентов и возвращает список (ArrayList) студентов
     *
     * @return список студентов если удалось прочитать или null если не удалось
     */
    public ArrayList<Student> getStudents() {
        ArrayList<Student> students = new ArrayList<>();
        Student student;
        //Проверка наличия файла ресурсов
        if (filePath == null || !(new File(filePath)).exists()) {
            logger.log(Level.WARNING, "Невозможно найти файл данных: " + filePath);
            logger.log(Level.WARNING, "Дальнейшее чтение из файла не возможно!");
            return null;
        }
        // Проверка наличия листа со студентами в файле
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            XSSFWorkbook xssfw = (new XSSFWorkbookFactory()).create(fileInputStream);
            if (xssfw.getSheetIndex(STUDENTSSHEETNAME) == -1) {
                logger.log(Level.WARNING, "В файле: " + filePath + " отсутсвует лист: " + STUDENTSSHEETNAME);
                logger.log(Level.WARNING, "Дальнейшее чтение из файла не возможно!");
                return null;
            }
            Iterator<Row> iterator = xssfw.getSheet(STUDENTSSHEETNAME).iterator(); // получаем итератор
            iterator.next(); // пропускаем строку с названием столбцов
            while (iterator.hasNext()) {
                Row row = iterator.next();
                student = new StudentBuilder(row.getCell(1).toString())
                        .setUniversityId(row.getCell(0).toString())
                        .setCurrentCourseNumber(Integer.parseInt(row.getCell(2).toString().substring(0, 1)))
                        .setAvgExamScore(Float.parseFloat(row.getCell(3).toString()))
                        .createStudent();
                students.add(student);
                logger.log(Level.FINE, "Успешно прочитан и добавлен в список университет: " + student.getFullName());
            }
            xssfw.close();
        } catch (IOException e) {
            logger.log(Level.WARNING, "Возникла ошибка при чтении файла: " + filePath);
            logger.log(Level.WARNING, e.getMessage());
            return null;
        }
        if (students.size() > 0) {
            logger.log(Level.INFO, "Успешно прочитан файл. Распознано студентов: " + Integer.toString(students.size()));

            return students;
        }
        return null;
    }


}
