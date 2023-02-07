package org.example;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;

public class XLSWriter {

    private final static String OUTPUTPATH = "src/main/resources/";

    public boolean writeXLS(Collection<Statistics> collection, String fileName) {
        XSSFWorkbook xssfwb = new XSSFWorkbook();
        XSSFSheet sheet = xssfwb.createSheet("Результат");
        sheet.setColumnWidth(0, 5000);
        sheet.setColumnWidth(1, 12000);
        sheet.setColumnWidth(2, 11000);
        sheet.setColumnWidth(3, 7000);
        sheet.setColumnWidth(4, 25000);


        CellStyle style1 = xssfwb.createCellStyle();
        Font font = xssfwb.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 20);
        style1.setFont(font);
        style1.setAlignment(HorizontalAlignment.CENTER);
        CellStyle style2 = xssfwb.createCellStyle();
        style2.setAlignment(HorizontalAlignment.RIGHT);

        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("Профиль");
        row.createCell(1).setCellValue("Количество университетов");
        row.createCell(2).setCellValue("Количество студенитов");
        row.createCell(3).setCellValue("Средний бал");
        row.createCell(4).setCellValue("Названия университетов");
        row.getCell(0).setCellStyle(style1);
        row.getCell(1).setCellStyle(style1);
        row.getCell(2).setCellStyle(style1);
        row.getCell(3).setCellStyle(style1);
        row.getCell(4).setCellStyle(style1);
        int rowNumber = 1;
        for (Statistics statElemet : collection) {
            row = sheet.createRow(rowNumber++);
            row.createCell(0).setCellValue(statElemet.getProfile().getRusName());
            row.createCell(1).setCellValue(Integer.toString(statElemet.getUniversityNumber()));
            row.createCell(2).setCellValue(Integer.toString(statElemet.getStudentNumber()));
            row.createCell(3).setCellValue(BigDecimal.valueOf(statElemet.getAverageScore())
                    .setScale(2, RoundingMode.HALF_UP).toPlainString());
            int lengthOfStringList = statElemet.getUniversityNames().toString().length();
            row.createCell(4).setCellValue(statElemet.getUniversityNames().toString().substring(1,lengthOfStringList-2));
            row.getCell(0).setCellStyle(style2);
            row.getCell(1).setCellStyle(style2);
            row.getCell(2).setCellStyle(style2);
            row.getCell(3).setCellStyle(style2);
        }


        boolean result = false;
        try (FileOutputStream out = new FileOutputStream(new File(OUTPUTPATH + fileName))) {
            xssfwb.write(out);
            result = true;
        } catch (IOException e) {
            System.out.println("не удалось записать файл: " + OUTPUTPATH + fileName);
            e.printStackTrace();
        }

        return result;


    }
}
