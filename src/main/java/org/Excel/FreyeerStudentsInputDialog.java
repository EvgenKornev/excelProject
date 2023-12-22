package org.Excel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Класс FreeyerStudentsInputDialog представляет диалоговое окно
 * для ввода количества бюджетных студентов.
 * @author Kornev E.A.
 * @version 1.0.0
 */
public class FreyeerStudentsInputDialog extends JDialog {
    private JSlider maxFreyeerStudentsSlider;
    private JButton okButton;
    private double maxFreyeerStudents;

    private int maxFreeyerLabel = GraphicInterface.maxStudents - GraphicInterface.maxTarget;

    /**
     * Конструктор класса FreeyerStudentsInputDialog.
     *
     * @param parent Родительское окно
     */
    public FreyeerStudentsInputDialog(JFrame parent) {
        super(parent, "Количество бюджетников:", true);

        maxFreyeerStudentsSlider = new JSlider(0, maxFreeyerLabel, maxFreeyerLabel);
        maxFreyeerStudentsSlider.setMajorTickSpacing((int) (maxFreeyerLabel * 0.5));
        maxFreyeerStudentsSlider.setMinorTickSpacing((int) (maxFreeyerLabel * 0.05));
        maxFreyeerStudentsSlider.setPaintTrack(true);
        maxFreyeerStudentsSlider.setPaintTicks(true);
        maxFreyeerStudentsSlider.setPaintLabels(true);

        JLabel valueLabel = new JLabel(String.valueOf(maxFreyeerStudentsSlider.getValue()));

        maxFreyeerStudentsSlider.addChangeListener(e -> {
            valueLabel.setText(String.valueOf(maxFreyeerStudentsSlider.getValue()));
        });

        okButton = new JButton("Подтвердить");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maxFreyeerStudents = maxFreyeerStudentsSlider.getValue();
                dispose();
            }
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(maxFreyeerStudentsSlider, BorderLayout.CENTER);
        mainPanel.add(valueLabel, BorderLayout.EAST);
        mainPanel.add(okButton, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(true);
    }

    /**
     * Получает введенное количество бюджетных студентов.
     *
     * @return Количество бюджетных студентов
     */
    public int getMaxFreyeerStudents() {
        return (int) maxFreyeerStudents;
    }
}
