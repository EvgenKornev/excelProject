package org.Excel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Класс TargetStudentsInputDialog предоставляет диалоговое окно для ввода количества целевых студентов.
 *@author Kornev E.A.
 *@version 1.0.0
 */
public class TargetStudentsInputDialog extends JDialog {
    private JSlider maxTargetStudentsSlider;
    private JButton okButton;
    private double maxTargetStudents;

    /**
     * Конструктор класса.
     *
     * @param parent Родительское окно
     */
    public TargetStudentsInputDialog(JFrame parent) {
        super(parent, "Количество целевиков:", true);

        maxTargetStudentsSlider = new JSlider(0, GraphicInterface.maxStudents, GraphicInterface.maxStudents);
        maxTargetStudentsSlider.setMajorTickSpacing((int) (GraphicInterface.maxStudents * 0.5));
        maxTargetStudentsSlider.setMinorTickSpacing((int) (GraphicInterface.maxStudents * 0.05));
        maxTargetStudentsSlider.setPaintTrack(true);
        maxTargetStudentsSlider.setPaintTicks(true);
        maxTargetStudentsSlider.setPaintLabels(true);

        JLabel valueLabel = new JLabel(String.valueOf(maxTargetStudentsSlider.getValue()));

        maxTargetStudentsSlider.addChangeListener(e -> {
            valueLabel.setText(String.valueOf(maxTargetStudentsSlider.getValue()));
        });

        okButton = new JButton("Подтвердить");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maxTargetStudents = maxTargetStudentsSlider.getValue();
                dispose();
            }
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(maxTargetStudentsSlider, BorderLayout.CENTER);
        mainPanel.add(valueLabel, BorderLayout.EAST);
        mainPanel.add(okButton, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(true);
    }

    /**
     * Получение значения количества целевых студентов.
     *
     * @return Количество целевых студентов
     */
    public int getMaxTargetStudents() {
        return (int) maxTargetStudents;
    }
}
