package org.Excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;


import java.io.FileInputStream;
import java.io.IOException;

public class Util {
    public static void excelReader(String file) throws IOException {
        HSSFWorkbook excelBook = new HSSFWorkbook(new FileInputStream(file)); //creating workbook and choosing path to the reading file
        HSSFSheet excelSheet = excelBook.getSheet("1"); // get access to sheet in excel file


        HSSFRow row = excelSheet.getRow(0); // choosing row(row index vertical)


        if(row.getCell(0).getCellType() == CellType.STRING){ // cellnum index(horizontal)
            String name = row.getCell(0).getStringCellValue();
            System.out.println(name);
        }

        if(row.getCell(1).getCellType() == CellType.NUMERIC){
            double math_ball = row.getCell(1).getNumericCellValue();
            System.out.println(math_ball);
        }

    }
}
