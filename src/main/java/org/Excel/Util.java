//Описание: Отвечает за чтение данных из файла Excel
// и преобразование их в список абитуриентов.
package org.Excel;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import static org.Excel.educationType.*;

public class Util {
    //Метод excelReader использует библиотеку Apache POI
    // для чтения данных из Excel-файла.
    // Информация о каждом абитуриенте представляется в виде
    // объекта Abiturient и добавляется в список.
    public static ArrayList<Abiturient> excelReader(String file) throws IOException {

        HSSFWorkbook excelBook = new HSSFWorkbook(new FileInputStream(file));
        HSSFSheet excelSheet = excelBook.getSheet("1");

        ArrayList<Abiturient> abiturients = new ArrayList<>();
        int rowIndex = 0;

        while (true) {
            HSSFRow row = excelSheet.getRow(rowIndex);
            if (row == null || row.getCell(0) == null || row.getCell(0).getStringCellValue().isEmpty()) {
                break;  // Прерываем цикл, если достигнут конец файла или встречена пустая строка
            }

            String name = row.getCell(0).getStringCellValue();
            double math_ball = row.getCell(1).getNumericCellValue();
            double phys_ball = row.getCell(2).getNumericCellValue();
            double lang_ball = row.getCell(3).getNumericCellValue();
            String educat = row.getCell(4).getStringCellValue();

            educationType type = null;
            switch (educat) {
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

            rowIndex++;
        }

        return abiturients;
    }
}
