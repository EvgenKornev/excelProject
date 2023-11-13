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

public class Util {
    public static ArrayList<Abiturient> excelReader(String file) throws IOException {
        HSSFWorkbook excelBook = new HSSFWorkbook(new FileInputStream(file)); //creating workbook and choosing path to the reading file
        HSSFSheet excelSheet = excelBook.getSheet("1"); // get access to sheet in excel file


        /**
        HSSFRow row = excelSheet.getRow(0); // choosing row(row index vertical)


        if(row.getCell(0).getCellType() == CellType.STRING){ // cellnum index(horizontal)
            String name = row.getCell(0).getStringCellValue();
            System.out.println(name);
        }

        if(row.getCell(1).getCellType() == CellType.NUMERIC){
            double math_ball = row.getCell(1).getNumericCellValue();
            System.out.println(math_ball);
        }
        */
        ArrayList<Abiturient> abiturients = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            HSSFRow row = excelSheet.getRow(i);
            String name = row.getCell(0).getStringCellValue();

            double math_ball = row.getCell(1).getNumericCellValue();

            double phys_ball = row.getCell(2).getNumericCellValue();

            double lang_ball = row.getCell(3).getNumericCellValue();

            String educat = row.getCell(4).getStringCellValue();
            switch (educat){ //switch education type construction
                case "целевое":
                    System.out.println("целевое");
                    break;
                case "платное":
                    System.out.println("платное");
                    break;
                case "бюджетное":
                    System.out.println("бюджетное");
                    break;
                default:
                    break;
            }
            /**System.out.println(name + " Математика: " + (int)math_ball + " Физика: " + (int)phys_ball + " Язык: " + (int)lang_ball + " Тип обучения:" + educat);
            System.out.println(); */


            Abiturient abiturient = new Abiturient(name, (int)(math_ball + phys_ball + lang_ball), educationType.PAYYER);
            abiturients.add(abiturient);

        }
        return abiturients;
    }

}
