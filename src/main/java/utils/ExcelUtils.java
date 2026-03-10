package utils;

import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.util.Iterator;

public class ExcelUtils {

    public static Object[][] getTestData(String filePath, String sheetName) {

        try (FileInputStream fis = new FileInputStream(filePath)) {

            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheet(sheetName);

            int rows = sheet.getPhysicalNumberOfRows();
            int cols = sheet.getRow(0).getLastCellNum();

            Object[][] data = new Object[rows - 1][cols];

            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next(); // skip header

            int rowIndex = 0;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                for (int col = 0; col < cols; col++) {
                    data[rowIndex][col] =
                            row.getCell(col).toString();
                }
                rowIndex++;
            }

            return data;

        } catch (Exception e) {
            throw new RuntimeException("Failed to read Excel file", e);
        }
    }
}

