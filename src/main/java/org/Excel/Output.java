package org.Excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Класс Output отвечает за запись данных в файл Excel.
 *@author Kornev E.A.
 *@version 1.0.0
 */
public class Output {
    /**
     * Метод excelWriter создает новый файл Excel, записывает данные о студентах в него и сохраняет файл.
     *
     * @param file Путь к файлу, в который будут записаны данные
     * @throws IOException Возникает при ошибках ввода-вывода
     */
    public static void excelWriter(String file) throws IOException {
        HSSFWorkbook book = new HSSFWorkbook();
        Sheet sheet = book.createSheet("1");

        educationType type = null;
        String stringType = null;

        for (int i = 0; i < Calculator.students.size(); i++) {
            Row row = sheet.createRow(i);

            Cell cell1 = row.createCell(0);
            cell1.setCellValue(Calculator.students.get(i).getName()); //name

            Cell cell2 = row.createCell(1);
            cell2.setCellValue(Calculator.students.get(i).getBalls());

            Cell cell3 = row.createCell(2);
            switch (type = Calculator.students.get(i).getType()) {
                case TARGET:
                    stringType = "целевое";
                    break;
                case PAYYER:
                    stringType = "платное";
                    break;
                case FREEYER:
                    stringType = "бюджетное";
                    break;
                default:
                    break;
            }
            cell3.setCellValue(stringType);
        }

        book.write(new FileOutputStream(file));
        book.close();
    }
}
