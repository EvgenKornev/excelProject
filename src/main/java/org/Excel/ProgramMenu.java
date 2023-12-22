package org.Excel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

/**
 * Класс ProgramMenu предоставляет информацию о программе и ее возможностях.
 *@author Kornev E.A.
 *@version 1.0.0
 */
public class ProgramMenu extends JFrame {
    public JButton backButton;

    /**
     * Конструктор класса.
     */
    public ProgramMenu() {
        this.setTitle("О программе");
        this.setSize(400, 200);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        backButton = new JButton("Назад");

        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        JLabel programName = new JLabel("Программа позволяет:");
        JLabel label1 = new JLabel("1. Получать файл расширения .xls");
        JLabel label2 = new JLabel("2. Указывать путь получения файла");
        JLabel label3 = new JLabel("3. Отображать данные файла в программе");
        JLabel label4 = new JLabel("4. Рассчитывать студентов по заданным параметрам");
        JLabel label5 = new JLabel("5. Указывать путь сохранения файла");
        JLabel label6 = new JLabel("6. Сохранять файл с расширением .xls");

        backButton.setAlignmentX(CENTER_ALIGNMENT);

        textPanel.add(programName);
        textPanel.add(label1);
        textPanel.add(label2);
        textPanel.add(label3);
        textPanel.add(label4);
        textPanel.add(label5);
        textPanel.add(label6);
        buttonPanel.add(backButton);

        mainPanel.add(textPanel);
        mainPanel.add(Box.createHorizontalStrut(12));
        mainPanel.add(buttonPanel);

        this.add(mainPanel);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
