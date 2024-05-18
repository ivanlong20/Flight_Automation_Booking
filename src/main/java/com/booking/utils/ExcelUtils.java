package com.booking.utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelUtils {
    private static final String projectRootPath = System.getProperty("user.dir");
    private static final String FILE_PATH = projectRootPath+"/src/main/resources/TestData.xlsx";

    public static Iterator<Object[]> getTestData() throws IOException, FileNotFoundException {
        List<Object[]> testData = new ArrayList<>();
        FileInputStream file = new FileInputStream(FILE_PATH);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0); // Assuming your data is in the first sheet

        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (row.getRowNum() == 0) {
                // Skip header row
                continue;
            }
            if (row.getRowNum() == 0) continue; // Skip header row
            String departureCity = row.getCell(0).getStringCellValue();
            String destinationCity = row.getCell(1).getStringCellValue();
            System.out.println(departureCity);
            String departureDate = row.getCell(2).getStringCellValue();
            String returnDate = row.getCell(3).getStringCellValue();
            String nationality = row.getCell(4).getStringCellValue();
            String passportID = row.getCell(5).getStringCellValue();
            String expiryDay = String.valueOf((int) row.getCell(6).getNumericCellValue());   // treated as string
            String expiryMonth = row.getCell(7).getStringCellValue(); // treated as string
            String expiryYear = String.valueOf((int) row.getCell(8).getNumericCellValue());  // treated as string
            String givenName = row.getCell(9).getStringCellValue();
            String surname = row.getCell(10).getStringCellValue();
            String gender = row.getCell(11).getStringCellValue();
            String birthDay = String.valueOf((int) row.getCell(12).getNumericCellValue());   // treated as string
            String birthMonth = row.getCell(13).getStringCellValue(); // treated as string
            String birthYear = String.valueOf((int) row.getCell(14).getNumericCellValue());  // treated as string
            String contactName = row.getCell(15).getStringCellValue();
            String email = row.getCell(16).getStringCellValue();
            String phone = String.valueOf((int) row.getCell(17).getNumericCellValue());;
            testData.add(new Object[]{
                    departureCity, destinationCity, departureDate, returnDate, nationality,
                    passportID, expiryDay, expiryMonth, expiryYear, givenName, surname,
                    gender, birthDay, birthMonth, birthYear, contactName, email, phone
            });
        }

        workbook.close();
        file.close();
        return testData.iterator();
    }
}
