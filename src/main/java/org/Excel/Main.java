package org.Excel;


import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<Abiturient> abiturients = Util.excelReader("D:\\excel\\students.xls"); // array Abiturient get values from excelReader method
        /*for(Abiturient a: abiturients){
            System.out.println(a.getName());
            System.out.println(a.getBalls());
            System.out.println(a.getType());
            System.out.println();
        }*/

        ArrayList<Abiturient> newAbiturients = Calculator.selection(abiturients);


    }
}