package org.Excel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class StartMenu extends JFrame{
    public JButton startButton;

    public JButton button1;
    public JButton button2;

    public StartMenu(){

        this.setTitle("Создание списка студентов из абитуриентов");
        this.setSize(600, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        startButton = new JButton("Начать");
        button1 = new JButton("Начать");
        button2 = new JButton("Начать");

        JLabel university = new JLabel("Белорусский Национальный Технический Университет");
        JLabel faculty = new JLabel("Факультет информационных технологий и робототехники");
        JLabel department = new JLabel("Кафедра программного обеспечения информационных систем и технологий");
        JLabel courseWork = new JLabel("Курсовая работа");
        JLabel discipline = new JLabel("По дисциплине: Программирование на Java");
        JLabel theme = new JLabel("Создание списка студентов из абитуриентов");

        JPanel buttonPanel = new JPanel();
        JPanel mainPanel = new JPanel();


        university.setFont(new Font( "Dialog", Font.PLAIN ,14));
        faculty.setFont(new Font( "Dialog", Font.PLAIN ,14));
        department.setFont(new Font( "Dialog", Font.PLAIN ,13));
        courseWork.setFont(new Font( "Dialog", Font.BOLD ,17));
        discipline.setFont(new Font( "Dialog", Font.PLAIN ,13));
        theme.setFont(new Font( "Dialog", Font.PLAIN ,13));

        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));




        mainPanel.add(university);
        mainPanel.add(faculty);
        mainPanel.add(department);
        mainPanel.add(Box.createHorizontalStrut(150));
        mainPanel.add(Box.createVerticalStrut(50));
        mainPanel.add(courseWork);
        mainPanel.add(Box.createHorizontalStrut(150));
        mainPanel.add(discipline);
        mainPanel.add(Box.createHorizontalStrut(400));
        mainPanel.add(theme);



        buttonPanel.add(startButton);

        this.add(mainPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);


        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GraphicInterface.run();
                dispose();
            }
        });

    }
}

