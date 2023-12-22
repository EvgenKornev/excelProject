package org.Excel;

import java.io.IOException; // Исключение ввода-вывода

/**
 * Класс Main представляет точку входа в программу.
 * Он устанавливает внешний вид графического интерфейса
 * и запускает его, создавая объект StartPage и делая его видимым.
 *@author Kornev E.A.
 *@version 1.0.0
 */
public class Main {

    /**
     * Метод main является точкой входа в программу.
     * Он запускает графический интерфейс, создавая объект StartPage
     * и делая его видимым.
     *
     * @param args Аргументы командной строки (не используются).
     * @throws IOException Исключение ввода-вывода, которое может
     *                     возникнуть при выполнении программы.
     */
    public static void main(String[] args) throws IOException {

        new StartPage().setVisible(true); // Создание объекта StartPage и его отображение
    }
}
