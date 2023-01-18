package org.example;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class UniversityInfoReader {
    private final String UNIVERSITIESSHEETNAME = "Университеты";
    private final String STUDENTSSHEETNAME = "Студенты";

    private static UniversityInfoReader uiReader;

    private String filePath;

    public static UniversityInfoReader getUIReader() {
        if (uiReader == null) {
            uiReader = new UniversityInfoReader();
        }
        return uiReader;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<University> getUniversities() {
        if (filePath == null || !(new File(filePath)).exists()) {
            System.out.println("Невозможно найти файл данных");
            System.out.println("Дальнейшее чтение из файла не возможно!");
            return null;
        }
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            XSSFWorkbook xssfw = (new XSSFWorkbookFactory()).create(fileInputStream);
            if (xssfw.getSheetIndex(UNIVERSITIESSHEETNAME) == -1) {
                System.out.println("В файле: " + filePath + " отсутсвует лист: " + UNIVERSITIESSHEETNAME);
                System.out.println("Дальнейшее чтение из файла не возможно!");
                return null;
            }




            xssfw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return null;
    }

    public ArrayList<Student> getStudents() {


        return null;
    }


}
