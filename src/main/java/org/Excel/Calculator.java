package org.Excel;

import java.util.ArrayList;

public class Calculator {
   static ArrayList<Abiturient> students = new ArrayList<>();//created a new array, into it in each of the three methods
    // we will add incoming students using the add method

    public static ArrayList<Abiturient> selection(ArrayList<Abiturient> abiturients) { //this is a static method
        //it takes an object of applicants from the main class and will put them in each of the three methods
addedTarget(abiturients);//the first method adds targets, we put our abiturients in it


        return null;
    }
    public static void addedTarget(ArrayList<Abiturient> abiturients){ //
        int counter = 0;
        int maxTarget = 1;
        int maxFreeyer = 3;
        int maxPayeer = (abiturients.size() - (maxFreeyer + maxFreeyer)) / 2;

                 //maxTarget need been input in Start Window

        for(int i = 0; i < abiturients.size(); i++){ //go through the entire array of abbiturients

            if(abiturients.get(i).getType() == educationType.TARGET){//at each iteration we compare the type of abiturient,
                //block of code is executed if there are matches

                if(maxTarget > 0) { //we check the counter to see if there are still free places, if maxTarget is not yet 0
                    //go to the code block
                    students.add(abiturients.get(i));//and add the abiturient to students
                    maxTarget--; //remove one place (person entered)
                    counter++;//increase the global receipt counter
                }
            }
            if (abiturients.get(i).getType() == educationType.FREEYER){

                if(maxFreeyer > 0) { //we check the counter to see if there are still free places, if maxFreeyer is not yet 0
                    //go to the code block
                    students.add(abiturients.get(i));//and add the abiturient to students
                    maxFreeyer--; //remove one place (person entered)
                    counter++;//increase the global receipt counter
                }
            }
            if (abiturients.get(i).getType() == educationType.PAYYER){

                if(maxPayeer > 0) { //we check the counter to see if there are still free places, if maxFreeyer is not yet 0
                    //go to the code block
                    students.add(abiturients.get(i)); //and add the abiturient to students
                    maxPayeer--; //remove one place (person entered)
                    counter++;//increase the global receipt counter
                }

        }
        }
        for(int i = 0; i < counter; i++){

            System.out.println(students.get(i).getName());
        }
    }
}
