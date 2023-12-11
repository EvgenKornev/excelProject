//Описание класса: Точка входа в программу, устанавливает
// внешний вид графического интерфейса и запускает его.

package org.Excel;

import java.io.IOException; //input-output exception


public class Main {
    public static void main(String[] args) throws IOException {
        //GraphicInterface.run(); // Запуск графического интерфейса

        new StartMenu().setVisible(true);

    }
}
