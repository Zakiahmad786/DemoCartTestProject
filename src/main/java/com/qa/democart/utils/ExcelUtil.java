package com.qa.democart.utils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ExcelUtil {

    private static String TEST_DATA_SHEET="./src/test/resources/testdata/OpenCartTestData.xlsx";
    private static Workbook book;
    private static Sheet sheet;
    public static Object[][] getTestData(String sheetName) {

        Object[][] data=null;
        try {
            FileInputStream file = new FileInputStream(TEST_DATA_SHEET);
            book = WorkbookFactory.create(file);
            sheet = book.getSheet(sheetName);
            int totalRow = sheet.getLastRowNum();
            int totalColumn = sheet.getRow(0).getLastCellNum();
            data = new Object[totalRow][totalColumn];
            for (int row = 0; row < totalRow; row++) {
                for (int column = 0; column < totalColumn; column++) {
                    data[row][column] = sheet.getRow(row + 1).getCell(column);
                }
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (InvalidFormatException format) {
            format.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
