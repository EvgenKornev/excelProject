package org.Excel;


import java.io.IOException;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<Abiturient> abiturients = Util.excelReader("D:\\excel\\students.xls"); // array Abiturient get values from excelReader method


        ArrayList<Abiturient> newStudents = Calculator.selection(abiturients);
        Output.excelWriter("D:\\excel\\students2.xls");

    }
}