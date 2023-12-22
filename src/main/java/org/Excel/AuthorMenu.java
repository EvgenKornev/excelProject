package org.Excel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

/**
 * Класс AuthorMenu представляет окно с информацией об авторе.
 *@author Kornev E.A.
 *@version 1.0.0
 */
public class AuthorMenu extends JFrame {
    public JButton authorButton;

    /**
     * Конструктор класса AuthorMenu.
     */
    public AuthorMenu() {
        this.setTitle("Об Авторе");
        this.setSize(260, 462);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);

        authorButton = new JButton("Назад");

        ImageIcon authorImage = new ImageIcon("author2.jpg");
        JLabel imageLabel = new JLabel(authorImage);
        JLabel nameLabel = new JLabel("КОРНЕВ ЕВГЕНИЙ АНДРЕЕВИЧ");
        JLabel groupLabel = new JLabel("ГРУППА 10702421");
        JLabel mailLabel = new JLabel("evgenkorneu@gmail.com");

        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        imageLabel.setAlignmentX(CENTER_ALIGNMENT);
        nameLabel.setAlignmentX(CENTER_ALIGNMENT);
        groupLabel.setAlignmentX(CENTER_ALIGNMENT);
        mailLabel.setAlignmentX(CENTER_ALIGNMENT);
        authorButton.setAlignmentX(CENTER_ALIGNMENT);

        // Добавляем слушатель для открытия ссылки по клику на картинку
        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openWebpage("https://www.example.com"); // Замените ссылку на нужную
            }
        });

        textPanel.add(nameLabel);
        textPanel.add(Box.createVerticalStrut(5));
        textPanel.add(groupLabel);
        textPanel.add(Box.createVerticalStrut(5));
        textPanel.add(mailLabel);

        buttonPanel.add(authorButton);

        mainPanel.add(imageLabel);
        mainPanel.add(textPanel);
        mainPanel.add(buttonPanel);

        this.add(mainPanel);

        authorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    /**
     * Метод для открытия ссылки в браузере.
     *
     * @param url Ссылка для открытия
     */
    private static void openWebpage(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

