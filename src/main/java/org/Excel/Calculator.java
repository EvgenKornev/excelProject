package org.Excel;

import java.util.ArrayList;

import java.util.Comparator;
import java.util.stream.Collectors;

public class Calculator {
    static ArrayList<Abiturient> students = new ArrayList<>();// created a new array, into it in each of the three
    // methods
    // we will add incoming students using the add method

    public static ArrayList<Abiturient> selection(ArrayList<Abiturient> abiturients) { // this is a static method
        // it takes an object of applicants from the main class and will put them in
        // each of the three methods
        // the first method adds targets, we put our abiturients in it
        // addStudents(abiturients);

        Comparator<Abiturient> compareByBalls = Comparator.comparing(Abiturient::getBalls).reversed();
        ArrayList<Abiturient> sortedAbiturients = abiturients.stream().sorted(compareByBalls)
                .collect(Collectors.toCollection(ArrayList::new));

        int maxTarget = 1;
        int maxFreeyer = 3;
        int maxPayeer = (sortedAbiturients.size() - (maxFreeyer + maxFreeyer)) / 2;

        // maxTarget need been input in Start Window

        for (int i = 0; i < sortedAbiturients.size(); i++) { // go through the entire array of abbiturients

            if (sortedAbiturients.get(i).getType() == educationType.TARGET) {// at each iteration we compare the type of
                // abiturient,
                // block of code is executed if there are matches

                if (maxTarget > 0) { // we check the counter to see if there are still free places, if maxTarget is
                    // not yet 0
                    // go to the code block
                    students.add(sortedAbiturients.get(i));// and add the abiturient to students
                    maxTarget--; // remove one place (person entered)
                    // counter++;//increase the global receipt counter
                }
            }
            if (sortedAbiturients.get(i).getType() == educationType.FREEYER) {

                if (maxFreeyer > 0) { // we check the counter to see if there are still free places, if maxFreeyer is
                    // not yet 0
                    // go to the code block
                    students.add(sortedAbiturients.get(i));// and add the abiturient to students
                    maxFreeyer--; // remove one place (person entered)
                    // counter++;//increase the global receipt counter
                }
            }
            if (sortedAbiturients.get(i).getType() == educationType.PAYYER) {

                if (maxPayeer > 0) { // we check the counter to see if there are still free places, if maxFreeyer is
                    // not yet 0
                    // go to the code block
                    students.add(sortedAbiturients.get(i)); // and add the abiturient to students
                    maxPayeer--; // remove one place (person entered)
                    // counter++;//increase the global receipt counter
                }
            }
        }
        return students;
    }
}
