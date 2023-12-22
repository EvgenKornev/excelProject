
package org.Excel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Графический интерфейс приложения для выборки студентов из абитуриентов.
 * Включает в себя функционал для чтения данных из Excel файла, проведения
 * расчетов и сохранения результатов.
 *@author Kornev E.A.
 *@version 1.0.0
 */
public class GraphicInterface extends JFrame {
    private JFrame mainframe; //general container
    private JTextArea outputTextArea;
    private ArrayList<Abiturient> selectedStudents;
    public static ArrayList<Abiturient> studentsSave;

    private File selectedFile;
    public static int maxTarget;
    private int maxFreeyer;
    public static int maxStudents;
    private boolean fileChosen = false; // Флаг для отслеживания выбора файла
    private boolean calculationsPerformed = false; // Флаг для отслеживания выполнения вычислений
    private int readButtonCounter = 0;

    private JButton calculateButton; // Объявляем поле, чтобы иметь доступ к нему в других методах
    private JButton writeButton; // Объявляем поле, чтобы иметь доступ к нему в других методах

    /**
     * Конструктор класса GraphicInterface.
     * Создает графический интерфейс приложения с кнопками для чтения, вычисления
     * и записи данных. Инициализирует основные элементы интерфейса, такие как
     * кнопки, меню, область вывода текста и обработчики событий.
     */
    GraphicInterface() {

        // Создание главного окна
        mainframe = new JFrame("Выборка студентов из абитуриентов");
        mainframe.setSize(500, 400);
        mainframe.setLocationRelativeTo(null);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Инициализация текстовой области вывода
        outputTextArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        outputTextArea.setEditable(false);

        // Инициализация кнопок и их начальное состояние
        JButton readButton = new JButton("Прочитать Excel файл");
        calculateButton = new JButton("Рассчитать");
        writeButton = new JButton("Сохранить");
        calculateButton.setEnabled(false);
        writeButton.setEnabled(false);

        // Инициализация меню
        JMenuBar menuBar = new JMenuBar();
        JMenu generalMenu = new JMenu("Информация");
        JMenuItem aboutAuthor = new JMenuItem("Об Авторе");
        JMenuItem aboutProgram = new JMenuItem("О программе");
        generalMenu.add(aboutAuthor);
        generalMenu.add(aboutProgram);
        menuBar.add(generalMenu);
        mainframe.setJMenuBar(menuBar);

        // Обработчики событий для пунктов меню
        aboutProgram.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProgramMenu().setVisible(true);
            }
        });
        aboutAuthor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AuthorMenu().setVisible(true);
            }
        });

        // Обработчик событий для кнопки "Прочитать Excel файл"
        readButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    readButtonCounter++;
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Выберите файл Excel");
                    fileChooser.setFileFilter(new FileNameExtensionFilter("Excel files", "xls"));
                    int result = fileChooser.showOpenDialog(mainframe);

                    if (result == JFileChooser.APPROVE_OPTION) {
                        selectedFile = fileChooser.getSelectedFile();
                        ArrayList<Abiturient> abiturients = Util.excelReader(selectedFile.getAbsolutePath());
                        outputTextArea.setText("");
                        displayOutput("Успешное чтение файла Excel");
                        displayOutput("Рабочая зона " + readButtonCounter);

                        for (Abiturient abiturient : abiturients) {
                            displayOutput(formatStudentOutput(abiturient));
                        }

                        fileChosen = true;
                        enableCalculateButton();
                        maxStudents = 0;
                        maxFreeyer = 0;
                        maxTarget = 0;
                        Calculator.acceptedFreeyer = 0;
                        Calculator.acceptedPayeer = 0;
                        Calculator.acceptedTarget = 0;
                        Calculator.acceptedTotal = 0;
                        Calculator.freeSpace = 0;
                    }
                } catch (IOException ex) {
                    displayOutput("Ошибка прочтения файла Excel");
                    ex.printStackTrace();
                }
            }

            /**
             * Форматирует вывод для отображения информации об абитуриенте.
             *
             * @param student Абитуриент
             * @return Строка с отформатированным выводом
             */
            private String formatStudentOutput(Abiturient student) {
                return "Абитуриент: " + student.getName() + " Баллы: " + student.getBalls() + " Тип: " + student.getOuputType();
            }

        });

        // Добавление обработчика событий для кнопки "Рассчитать"
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    requestUserInput();
                    ArrayList<Abiturient> abiturients = Util.excelReader(selectedFile.getAbsolutePath());
                    selectedStudents = Calculator.selection(abiturients, maxTarget, maxFreeyer, maxStudents);

                    outputTextArea.setText("");
                    displayOutput(formatCalculateOutput(selectedStudents));
                    studentsSave = selectedStudents;

                    displayOutput("Произведена выборка");
                    displayOutput("Общее количество принятых студентов: " + String.valueOf(Calculator.acceptedTotal + " (из " + maxStudents + " заданных)"));
                    displayOutput("Количество принятых целевиков: " + String.valueOf(Calculator.acceptedTarget  + " (из " + maxTarget + " заданных)"));
                    displayOutput("Количество принятых бюджетников: " + String.valueOf(Calculator.acceptedFreeyer  + " (из " + maxFreeyer + " заданных)"));
                    displayOutput("Количество принятых платников: " + String.valueOf(Calculator.acceptedPayeer  + " (из " + (maxStudents - maxTarget - maxFreeyer) + " заданных)"));
                    displayOutput("Количество свободных мест: " + String.valueOf(Calculator.freeSpace ));

                    calculationsPerformed = true;
                    enableWriteButton();
                } catch (IOException ex) {
                    displayOutput("Ошибка чтения файла Excel");
                    ex.printStackTrace();
                }
            }
        });

        // Добавление обработчика событий для кнопки "Сохранить"
        writeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Выберите место сохранения Excel файла");
                    fileChooser.setFileFilter(new FileNameExtensionFilter("Excel files", "xls"));
                    int result = fileChooser.showSaveDialog(mainframe);

                    if (result == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        String filePath = selectedFile.getAbsolutePath();

                        if (!filePath.endsWith(".xls")) {
                            filePath += ".xls";
                        }

                        Output.excelWriter(filePath);
                        displayOutput(formatWriteOutput(filePath));
                    }
                } catch (IOException ex) {
                    displayOutput("Ошибка записи файла Excel");
                    ex.printStackTrace();
                }
            }
        });

        // Инициализация панели с кнопками и добавление элементов на главное окно
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(readButton);
        panel.add(calculateButton);
        panel.add(writeButton);
        mainframe.add(scrollPane, BorderLayout.CENTER);
        mainframe.add(panel, BorderLayout.SOUTH);
        mainframe.setVisible(true);
    }
    /**
     * Включает или выключает кнопку "Рассчитать" в зависимости от того, выбран
     * ли файл Excel.
     */
    private void enableCalculateButton() {
        if (fileChosen) {
            calculateButton.setEnabled(true);
        } else {
            calculateButton.setEnabled(false);
        }
    }
    /**
     * Включает или выключает кнопку "Сохранить" в зависимости от того, выполнены
     * ли расчеты.
     */
    private void enableWriteButton() {
        if (calculationsPerformed) {
            writeButton.setEnabled(true);

        } else {
            writeButton.setEnabled(false);
        }
    }
    /**
     * Форматирует вывод для отображения выбранных студентов после расчетов.
     *
     * @param students Список выбранных студентов
     * @return Строка с отформатированным выводом
     */
    private String formatCalculateOutput(ArrayList<Abiturient> students) {
        StringBuilder output = new StringBuilder("Выбранные студенты:\n");
        for (Abiturient student : students) {
            output.append("Студент: ").append(student.getName())
                    .append(" Баллы: ").append(student.getBalls())
                    .append(" Тип: ").append(student.getOuputType()).append("\n");
        }
        return output.toString();
    }
    /**
     * Форматирует вывод для отображения информации о сохранении файла Excel.
     *
     * @param filePath Путь к сохраненному файлу
     * @return Строка с отформатированным выводом
     */
    private String formatWriteOutput(String filePath) {
        return "Успешно сохранено. Файл записан в: " + filePath;
    }
    /**
     * Запрашивает у пользователя ввод необходимых данных для выполнения расчетов,
     * таких как максимальное количество студентов, целевых и бюджетных мест.
     */
    private void requestUserInput() {
        try {
            // Запрос максимального числа студентов у пользователя
            String maxStudentsInput = JOptionPane.showInputDialog(mainframe, "Введите максимальное количество всех студентов");
            maxStudents = Integer.parseInt(maxStudentsInput);


            // Запрос максимального числа целевых студентов у пользователя
            TargetStudentsInputDialog targetStudentsInputDialog = new TargetStudentsInputDialog(mainframe);
            targetStudentsInputDialog.setVisible(true); // Блокирующий вызов, ждет, пока пользователь не закроет диалог
            maxTarget = targetStudentsInputDialog.getMaxTargetStudents();

            // Запрос максимального числа бюджетных студентов у пользователя
            //String maxFreeyerInput = JOptionPane.showInputDialog(mainframe, "Введите максимальное количество студентов-бюджетников");
            //maxFreeyer = Integer.parseInt(maxFreeyerInput);
            FreyeerStudentsInputDialog freyeerStudentsInputDialog = new FreyeerStudentsInputDialog(mainframe);
            freyeerStudentsInputDialog.setVisible(true); // Блокирующий вызов, ждет, пока пользователь не закроет диалог
            maxFreeyer = freyeerStudentsInputDialog.getMaxFreyeerStudents();


            // Сбрасываем флаг при новом запросе ввода
            fileChosen = false;
            enableCalculateButton(); // Проверяем, нужно ли выключить кнопку "Рассчитать"
        } catch (NumberFormatException e) {
            // Обработка ошибки ввода (например, если пользователь ввел не число)
            JOptionPane.showMessageDialog(mainframe, "Неправильный ввод. Пожалуйста, введите числовое значение", "Ошибка", JOptionPane.ERROR_MESSAGE);
            requestUserInput();
        }
    }

    private void displayOutput(String message) {
        outputTextArea.append(message + "\n");
    }

    public static void run() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GraphicInterface();
            }
        });
    }
}

