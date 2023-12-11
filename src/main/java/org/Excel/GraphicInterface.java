//Описание: Создает графический интерфейс приложения с
// кнопками для чтения, вычисления и записи данных.



package org.Excel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class GraphicInterface extends JFrame {
    private JFrame mainframe; //general container
    private JTextArea outputTextArea;
    private ArrayList<Abiturient> selectedStudents;

    private File selectedFile;
    public int maxTarget;
    private int maxFreeyer;
    private int maxStudents;
    private boolean fileChosen = false; // Флаг для отслеживания выбора файла
    private boolean calculationsPerformed = false; // Флаг для отслеживания выполнения вычислений

    private JButton calculateButton; // Объявляем поле, чтобы иметь доступ к нему в других методах
    private JButton writeButton; // Объявляем поле, чтобы иметь доступ к нему в других методах

    GraphicInterface() {


        mainframe = new JFrame("Выборка студентов из абитуриентов");
        mainframe.setSize(500, 400);
        mainframe.setLocationRelativeTo(null);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        outputTextArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        outputTextArea.setEditable(false);

        JButton readButton = new JButton("Прочитать Excel файл");
        calculateButton = new JButton("Рассчитать");
        writeButton = new JButton("Сохранить");

        // Начально делаем кнопку "Рассчитать" и "Сохранить" неактивными
        calculateButton.setEnabled(false);
        writeButton.setEnabled(false);

        JMenuBar menuBar = new JMenuBar();

        JMenu generalMenu = new JMenu("Информация");

        JMenuItem aboutAuthor = new JMenuItem("Об Авторе");
        JMenuItem aboutProgram = new JMenuItem("О программе");

        generalMenu.add(aboutAuthor);
        generalMenu.add(aboutProgram);

        menuBar.add(generalMenu);

        mainframe.setJMenuBar(menuBar);

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
                //mainframe.dispose();




            }
        });


        // В методе actionPerformed класса GraphicInterface для кнопки "Read Excel"
        readButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Открываем проводник для выбора файла
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Выберите файл Excel");
                    fileChooser.setFileFilter(new FileNameExtensionFilter("Excel files", "xls"));

                    int result = fileChooser.showOpenDialog(mainframe);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        selectedFile = fileChooser.getSelectedFile();
                        ArrayList<Abiturient> abiturients = Util.excelReader(selectedFile.getAbsolutePath());

                        displayOutput("Успешное чтение файла Excel");

                        for (Abiturient abiturient : abiturients) {
                            displayOutput(formatStudentOutput(abiturient));
                        }

                        // Устанавливаем флаг, что файл был выбран
                        fileChosen = true;
                        enableCalculateButton(); // Проверяем, нужно ли включить кнопку "Рассчитать"
                    }
                } catch (IOException ex) {
                    displayOutput("Ошибка прочетния файла Excel");
                    ex.printStackTrace();
                }
            }

            private String formatStudentOutput(Abiturient student) {
                return "Абитуриент: " + student.getName() + " Баллы: " + student.getBalls() + " Тип: " + student.getOuputType();
            }

        });

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    // Запрашиваем значения у пользователя
                    requestUserInput();

                    ArrayList<Abiturient> abiturients = Util.excelReader(selectedFile.getAbsolutePath());
                    selectedStudents = Calculator.selection(abiturients, maxTarget, maxFreeyer, maxStudents);

                    outputTextArea.setText(""); // Очищаем текстовую область перед новым выводом

                    displayOutput(formatCalculateOutput(selectedStudents));
                    displayOutput("Произведена выборка");
                    displayOutput("Общее количество принятых студентов: " + String.valueOf(Calculator.acceptedTotal));
                    displayOutput("Количество принятых целевиков: " + String.valueOf(Calculator.acceptedTarget));
                    displayOutput("Количество принятых бюджетников: " + String.valueOf(Calculator.acceptedFreeyer));
                    displayOutput("Количество принятых платников: " + String.valueOf(Calculator.acceptedPayeer));
                    displayOutput("Количество свободных мест: " + String.valueOf(Calculator.freeSpace));

                    // Устанавливаем флаг, что вычисления выполнены
                    calculationsPerformed = true;
                    enableWriteButton(); // Проверяем, нужно ли включить кнопку "Сохранить"
                } catch (IOException ex) {
                    displayOutput("Ошибка чтения файла Excel");
                    ex.printStackTrace();
                }
            }
        });

        writeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Открываем проводник для выбора места сохранения файла
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Выберите место сохранения Excel файла");
                    fileChooser.setFileFilter(new FileNameExtensionFilter("Excel files", "xls"));

                    int result = fileChooser.showSaveDialog(mainframe);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        String filePath = selectedFile.getAbsolutePath();

                        // Добавляем расширение .xls, если оно не указано
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

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(readButton);
        panel.add(calculateButton);
        panel.add(writeButton);

        mainframe.add(scrollPane, BorderLayout.CENTER);
        mainframe.add(panel, BorderLayout.SOUTH);
        mainframe.setVisible(true);

    }

    private void enableCalculateButton() {
        if (fileChosen) {
            calculateButton.setEnabled(true);
        } else {
            calculateButton.setEnabled(false);
        }
    }

    private void enableWriteButton() {
        if (calculationsPerformed) {
            writeButton.setEnabled(true);
        } else {
            writeButton.setEnabled(false);
        }
    }

    private String formatCalculateOutput(ArrayList<Abiturient> students) {
        StringBuilder output = new StringBuilder("Выбранные студенты:\n");
        for (Abiturient student : students) {
            output.append("Студент: ").append(student.getName())
                    .append(" Баллы: ").append(student.getBalls())
                    .append(" Тип: ").append(student.getOuputType()).append("\n");
        }
        return output.toString();
    }

    private String formatWriteOutput(String filePath) {
        return "Успешно сохранено. Файл записан в: " + filePath;
    }

    private void requestUserInput() {
        try {
            // Запрос максимального числа студентов у пользователя
            String maxStudentsInput = JOptionPane.showInputDialog(mainframe, "Введите максимальное количество всех студентов");
            maxStudents = Integer.parseInt(maxStudentsInput);

            // Запрос максимального числа целевых студентов у пользователя
            String maxTargetInput = JOptionPane.showInputDialog(mainframe, "Введите максимальное количество студентов-целевиков:");
            maxTarget = Integer.parseInt(maxTargetInput);

            // Запрос максимального числа бюджетных студентов у пользователя
            String maxFreeyerInput = JOptionPane.showInputDialog(mainframe, "Введите максимальное количество студентов-бюджетников");
            maxFreeyer = Integer.parseInt(maxFreeyerInput);

            // Сбрасываем флаг при новом запросе ввода
            fileChosen = false;
            enableCalculateButton(); // Проверяем, нужно ли выключить кнопку "Рассчитать"
        } catch (NumberFormatException e) {
            // Обработка ошибки ввода (например, если пользователь ввел не число)
            JOptionPane.showMessageDialog(mainframe, "Неправильный ввод. Пожалуйста, введите числовое значение", "Ошибка", JOptionPane.ERROR_MESSAGE);
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

