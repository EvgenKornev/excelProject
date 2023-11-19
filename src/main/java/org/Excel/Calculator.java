package org.Excel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Calculator {
    static ArrayList<Abiturient> students = new ArrayList<>();

    public static ArrayList<Abiturient> selection(ArrayList<Abiturient> abiturients, int maxTarget, int maxFreeyer, int maxStudents) {
        Comparator<Abiturient> compareByBalls = Comparator.comparing(Abiturient::getBalls).reversed();
        ArrayList<Abiturient> sortedAbiturients = abiturients.stream().sorted(compareByBalls)
                .collect(Collectors.toCollection(ArrayList::new));

        int maxPayeer = maxStudents - maxTarget - maxFreeyer;

        for (int i = 0; i < sortedAbiturients.size(); i++) {
            if (sortedAbiturients.get(i).getType() == educationType.TARGET && maxTarget > 0) {
                students.add(sortedAbiturients.get(i));
                maxTarget--;
            } else if (sortedAbiturients.get(i).getType() == educationType.FREEYER && maxFreeyer > 0) {
                students.add(sortedAbiturients.get(i));
                maxFreeyer--;
            } else if (sortedAbiturients.get(i).getType() == educationType.PAYYER && maxPayeer > 0) {
                students.add(sortedAbiturients.get(i));
                maxPayeer--;
            }
        }

        // Добавляем "свободное место" для недостающих студентов
        while (students.size() < maxStudents) {
            if (maxTarget > 0) {
                students.add(new Abiturient("Свободное место", 0, educationType.TARGET));
                maxTarget--;
            } else if (maxFreeyer > 0) {
                students.add(new Abiturient("Свободное место", 0, educationType.FREEYER));
                maxFreeyer--;
            } else if (maxPayeer > 0) {
                students.add(new Abiturient("Свободное место", 0, educationType.PAYYER));
                maxPayeer--;
            } else {
                break; // Если свободных мест больше нет, выходим из цикла
            }
        }

        return students;
    }
}
