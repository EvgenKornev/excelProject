//Описание: Класс, выполняющий расчет и выборку студентов
// в соответствии с заданными параметрами.
package org.Excel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Класс, выполняющий расчет и выборку студентов в соответствии с заданными параметрами.
 *@author Kornev E.A.
 *@version 1.0.0
 */
public class Calculator {
    // Список выбранных студентов
    static ArrayList<Abiturient> students = new ArrayList<>();
    // Счетчики принятых студентов по типам
    static int acceptedTotal = 0;
    static int acceptedTarget = 0;
    static int acceptedFreeyer = 0;
    static int acceptedPayeer = 0;
    // Количество свободных мест
    static int freeSpace = 0;

    /**
     * Метод selection принимает список абитуриентов и ограничения
     * на количество целевых, бюджетных и общее количество студентов.
     * Он сортирует абитуриентов по баллам и выбирает студентов
     * в соответствии с заданными ограничениями.
     *
     * @param abiturients  Список абитуриентов
     * @param maxTarget    Максимальное количество целевых студентов
     * @param maxFreeyer   Максимальное количество бюджетных студентов
     * @param maxStudents  Общее максимальное количество студентов
     * @return Список выбранных студентов
     */
    public static ArrayList<Abiturient> selection(ArrayList<Abiturient> abiturients, int maxTarget, int maxFreeyer, int maxStudents) {
        // Компаратор для сортировки абитуриентов по баллам и типу обучения
        Comparator<Abiturient> compareByBallsAndType = Comparator
                .comparing((Abiturient ab) -> ab.getType())
                .thenComparing(Comparator.comparingInt(Abiturient::getBalls).reversed());

        // Сортировка абитуриентов
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

        // Выбор студентов в соответствии с ограничениями
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

        // Добавление "свободного места" для недостающих студентов
        while (students.size() < maxStudents) {
            if (maxTarget > 0) {
                maxTarget--;
                freeSpace++;
            } else if (maxFreeyer > 0) {
                maxFreeyer--;
                freeSpace++;
            } else if (maxPayeer > 0) {
                maxPayeer--;
                freeSpace++;
            } else {
                break; // Если свободных мест больше нет, выходим из цикла
            }
        }

        return students;
    }
}
