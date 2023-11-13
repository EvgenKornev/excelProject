package org.Excel;


import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<Abiturient> abiturients = Util.excelReader("D:\\excel\\students.xls");
        System.out.println(abiturients);
        for(Abiturient a: abiturients){
            System.out.println(a.getType());

        }
    }
}