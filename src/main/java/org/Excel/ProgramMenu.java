package org.Excel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

public class ProgramMenu extends JFrame {
    public JButton authorButton;

    public ProgramMenu() {
        this.setTitle("О программе");
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        authorButton = new JButton("Назад");


        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));


        JLabel programName = new JLabel("Составление списка студентов из абитуриентов");

        authorButton.setAlignmentX(CENTER_ALIGNMENT);

        textPanel.add(programName);
        buttonPanel.add(authorButton);

        mainPanel.add(textPanel);
        mainPanel.add(Box.createHorizontalStrut(12));
        mainPanel.add(buttonPanel);

        this.add(mainPanel);

        authorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //GraphicInterface.run();
                dispose();
            }
        });
    }

    // Метод для открытия ссылки в браузере
    private static void openWebpage(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
