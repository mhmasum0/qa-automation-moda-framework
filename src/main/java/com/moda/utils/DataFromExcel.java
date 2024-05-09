package com.moda.utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class DataFromExcel {

    private DataFromExcel() {
        throw new IllegalStateException("Utility class");
    }

    public static Object[][] getDataFromExcel(String excelFilePath, String sheetName, int columnCount ) {

        Object[][] data = new Object[0][];
        try(FileInputStream inputStream = new FileInputStream(new File(excelFilePath))) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheet(sheetName);

            int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
            data = new Object[rowCount][columnCount];

            for (int i = 1; i <= rowCount; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j <columnCount; j++) {
                    data[i - 1][j] = row.getCell(j).toString();
                }
            }
        } catch (IOException e) {
            LogHelper.getLogger().info(e.getMessage());
        }
        return data;
    }
}
