//Описание: Класс, выполняющий расчет и выборку студентов
// в соответствии с заданными параметрами.
package org.Excel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Calculator {
    static ArrayList<Abiturient> students = new ArrayList<>();
    static int acceptedTotal = 0;
    static int acceptedTarget = 0;
    static int acceptedFreeyer = 0;
    static int acceptedPayeer = 0;
    static int freeSpace = 0;

    // Метод selection принимает список абитуриентов и ограничения
    // на количество целевых, бюджетных и общее количество студентов.
    // Он сортирует абитуриентов по баллам и выбирает студентов
    // в соответствии с заданными ограничениями.
    public static ArrayList<Abiturient> selection(ArrayList<Abiturient> abiturients, int maxTarget, int maxFreeyer, int maxStudents) {
        Comparator<Abiturient> compareByBallsAndType = Comparator
                .comparing((Abiturient ab) -> ab.getType())
                .thenComparing(Comparator.comparingInt(Abiturient::getBalls).reversed());

        ArrayList<Abiturient> sortedAbiturients = abiturients.stream()
                .sorted(compareByBallsAndType)
                .collect(Collectors.toCollection(ArrayList::new));

        int maxPayeer = maxStudents - maxTarget - maxFreeyer;


        // Приоритет целевиков перед бюджетниками
        if (maxTarget + maxFreeyer > maxStudents) {
            maxTarget = Math.min(maxTarget, maxStudents);
            maxFreeyer = Math.max(0, maxStudents - maxTarget);
            maxPayeer = 0;
        }

        for (int i = 0; i < sortedAbiturients.size(); i++) {
            if (sortedAbiturients.get(i).getType() == educationType.TARGET && maxTarget > 0) {
                students.add(sortedAbiturients.get(i));
                maxTarget--;
                acceptedTotal++;
                acceptedTarget++;
            } else if (sortedAbiturients.get(i).getType() == educationType.FREEYER && maxFreeyer > 0) {
                students.add(sortedAbiturients.get(i));
                maxFreeyer--;
                acceptedTotal++;
                acceptedFreeyer++;
            } else if (sortedAbiturients.get(i).getType() == educationType.PAYYER && maxPayeer > 0) {
                students.add(sortedAbiturients.get(i));
                maxPayeer--;
                acceptedTotal++;
                acceptedPayeer++;
            }
        }

        // Добавляем "свободное место" для недостающих студентов
        while (students.size() < maxStudents) {
            if (maxTarget > 0) {
                //students.add(new Abiturient("Свободное место", 0, educationType.TARGET));
                maxTarget--;
                acceptedTarget--;
                acceptedTotal--;
                freeSpace++;
            } else if (maxFreeyer > 0) {
                //students.add(new Abiturient("Свободное место", 0, educationType.FREEYER));
                maxFreeyer--;
                acceptedFreeyer--;
                acceptedTotal--;
                freeSpace++;
            } else if (maxPayeer > 0) {
                //students.add(new Abiturient("Свободное место", 0, educationType.PAYYER));
                maxPayeer--;
                acceptedPayeer--;
                acceptedTotal--;
                freeSpace++;
            } else {
                break; // Если свободных мест больше нет, выходим из цикла
            }
        }
        //freeSpace = maxStudents - acceptedTotal;
        /*// Выводим информацию о количестве принятых студентов по типам
        System.out.println("Общее количество принятых студентов: " + acceptedTotal);
        System.out.println("Количество принятых целевиков: " + acceptedTarget);
        System.out.println("Количество принятых бюджетников: " + acceptedFreeyer);
        System.out.println("Количество принятых платников: " + acceptedPayeer);*/

        return students;
    }
}
