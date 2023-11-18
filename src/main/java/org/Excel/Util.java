package org.Excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;


import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.Excel.educationType.*;

public class Util {
    public static ArrayList<Abiturient> excelReader(String file) throws IOException { // this method read info from excel file and return this in Abiturient

        HSSFWorkbook excelBook = new HSSFWorkbook(new FileInputStream(file)); //creating workbook and choosing path to the reading file
        HSSFSheet excelSheet = excelBook.getSheet("1"); // get access to sheet in excel file


        ArrayList<Abiturient> abiturients = new ArrayList<>();
        for (int i = 0; i < 10; i++) { // read info from cells
            HSSFRow row = excelSheet.getRow(i);
            String name = row.getCell(0).getStringCellValue();

            double math_ball = row.getCell(1).getNumericCellValue();

            double phys_ball = row.getCell(2).getNumericCellValue();

            double lang_ball = row.getCell(3).getNumericCellValue();

            String educat = row.getCell(4).getStringCellValue();

            educationType type = null;

            switch (educat) { //switch education type construction
                case "целевое":
                    type = TARGET;
                    break;
                case "платное":
                    type = PAYYER;
                    break;
                case "бюджетное":
                    type = FREEYER;
                    break;
                default:
                    break;
            }

            Abiturient abiturient = new Abiturient(name, (int) (math_ball + phys_ball + lang_ball), type);
            abiturients.add(abiturient);

        }
        return abiturients;
    }

}
