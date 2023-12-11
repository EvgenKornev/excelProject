package org.Excel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FreyeerStudentsInputDialog extends JDialog {
    private JSlider maxFreyeerStudentsSlider;
    private JButton okButton;
    private double maxFreyeerStudents;

    private int maxFreeyerLabel =GraphicInterface.maxStudents - GraphicInterface.maxTarget;
    public FreyeerStudentsInputDialog(JFrame parent) {
        super(parent, "Количество бюджетников:", true);



        maxFreyeerStudentsSlider = new JSlider(0, maxFreeyerLabel, maxFreeyerLabel); // от 0 до 100, начальное значение 50
        maxFreyeerStudentsSlider.setMajorTickSpacing((int)(maxFreeyerLabel * 0.5));
        maxFreyeerStudentsSlider.setMinorTickSpacing((int)(maxFreeyerLabel * 0.05));
        //maxTargetStudentsSlider.setSnapToTicks(true); // Включаем привязку ползунка к ticks
        maxFreyeerStudentsSlider.setPaintTrack(true); // Отображаем дорожку ползунка
        maxFreyeerStudentsSlider.setPaintTicks(true);
        maxFreyeerStudentsSlider.setPaintLabels(true);

        JLabel valueLabel = new JLabel(String.valueOf(maxFreyeerStudentsSlider.getValue()));

        maxFreyeerStudentsSlider.addChangeListener(e -> {
            // Обновляем текст метки при изменении положения ползунка
            valueLabel.setText(String.valueOf(maxFreyeerStudentsSlider.getValue()));
        });


        okButton = new JButton("Подтвердить");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maxFreyeerStudents = maxFreyeerStudentsSlider.getValue();
                dispose(); // Закрываем диалоговое окно после нажатия OK
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

    public int getMaxFreyeerStudents() {
        return (int) maxFreyeerStudents;
    }
}
