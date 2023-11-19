package org.Excel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GraphicInterface {
    private JFrame frame;
    private JTextArea outputTextArea;
    private ArrayList<Abiturient> selectedStudents;
    private int maxTarget;
    private int maxFreeyer;
    private int maxStudents;

    private GraphicInterface() {
        frame = new JFrame("Выборка студентов из абитуриентов");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        outputTextArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        outputTextArea.setEditable(false);

        JButton readButton = new JButton("Прочитать Excel файл");
        JButton calculateButton = new JButton("Рассчитать");
        JButton writeButton = new JButton("Сохранить");

        // В методе actionPerformed класса GraphicInterface для кнопки "Read Excel"
        readButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Открываем проводник для выбора файла
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Выберите файл Excel");
                    fileChooser.setFileFilter(new FileNameExtensionFilter("Excel files", "xls"));

                    int result = fileChooser.showOpenDialog(frame);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        ArrayList<Abiturient> abiturients = Util.excelReader(selectedFile.getAbsolutePath());

                        displayOutput("Успешное чтение файла Excel");

                        for (Abiturient abiturient : abiturients) {
                            displayOutput(formatStudentOutput(abiturient));
                        }
                    }
                } catch (IOException ex) {
                    displayOutput("Ошибка прочетния файла Excel");
                    ex.printStackTrace();
                }
            }

            private String formatStudentOutput(Abiturient student) {
                return "Студент: " + student.getName() + " Баллы: " + student.getBalls() + " Тип: " + student.getType();
            }

        });


        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Запрашиваем значения у пользователя
                    requestUserInput();

                    ArrayList<Abiturient> abiturients = Util.excelReader("D:\\excel\\students.xls");
                    selectedStudents = Calculator.selection(abiturients, maxTarget, maxFreeyer, maxStudents);

                    outputTextArea.setText(""); // Очищаем текстовую область перед новым выводом

                    displayOutput(formatCalculateOutput(selectedStudents));
                    displayOutput("Произведена выборка");
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

                    int result = fileChooser.showSaveDialog(frame);
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

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private String formatCalculateOutput(ArrayList<Abiturient> students) {
        StringBuilder output = new StringBuilder("Выбранные студенты:\n");
        for (Abiturient student : students) {
            output.append("Имя: ").append(student.getName())
                    .append(" Баллы: ").append(student.getBalls())
                    .append(" Тип: ").append(student.getType()).append("\n");
        }
        return output.toString();
    }

    private String formatWriteOutput(String filePath) {
        return "Успешно сохранено. Файл записан в: " + filePath;
    }


    private void requestUserInput() {
        try {
            // Запрос максимального числа студентов у пользователя
            String maxStudentsInput = JOptionPane.showInputDialog(frame, "Введите максимальное количество всех студентов");
            maxStudents = Integer.parseInt(maxStudentsInput);

            // Запрос максимального числа целевых студентов у пользователя
            String maxTargetInput = JOptionPane.showInputDialog(frame, "Введите максимальное количество студентов-целевиков:");
            maxTarget = Integer.parseInt(maxTargetInput);

            // Запрос максимального числа бюджетных студентов у пользователя
            String maxFreeyerInput = JOptionPane.showInputDialog(frame, "Введите максимальное количество студентов-бюджетников");
            maxFreeyer = Integer.parseInt(maxFreeyerInput);
        } catch (NumberFormatException e) {
            // Обработка ошибки ввода (например, если пользователь ввел не число)
            JOptionPane.showMessageDialog(frame, "Неправильный ввод. Пожалуйста, введите числовое значение", "Ошибка", JOptionPane.ERROR_MESSAGE);
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
