package org.Excel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class TargetStudentsInputDialog extends JDialog {
    private JSlider maxTargetStudentsSlider;
    private JButton okButton;
    private double maxTargetStudents;

    public TargetStudentsInputDialog(JFrame parent) {
        super(parent, "Количество целевиков:", true);



        maxTargetStudentsSlider = new JSlider(0, GraphicInterface.maxStudents, GraphicInterface.maxStudents); // от 0 до 100, начальное значение 50
        maxTargetStudentsSlider.setMajorTickSpacing((int)(GraphicInterface.maxStudents * 0.5));
        maxTargetStudentsSlider.setMinorTickSpacing((int)(GraphicInterface.maxStudents * 0.05));
        //maxTargetStudentsSlider.setSnapToTicks(true); // Включаем привязку ползунка к ticks
        maxTargetStudentsSlider.setPaintTrack(true); // Отображаем дорожку ползунка
        maxTargetStudentsSlider.setPaintTicks(true);
        maxTargetStudentsSlider.setPaintLabels(true);

        JLabel valueLabel = new JLabel(String.valueOf(maxTargetStudentsSlider.getValue()));

        maxTargetStudentsSlider.addChangeListener(e -> {
            // Обновляем текст метки при изменении положения ползунка
            valueLabel.setText(String.valueOf(maxTargetStudentsSlider.getValue()));
        });


        okButton = new JButton("Подтвердить");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maxTargetStudents = maxTargetStudentsSlider.getValue();
                dispose(); // Закрываем диалоговое окно после нажатия OK
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

    public int getMaxTargetStudents() {
        return (int)maxTargetStudents;
    }
}
