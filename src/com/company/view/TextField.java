package com.company.view;
import com.company.model.entites.Player;
import com.company.model.gamestate.Level1State;
import com.company.view.main.GamePanel;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class TextField extends JFrame
{
    // Текстовые поля
    JTextField smallField;

    public TextField()
    {
        super("ВВЕДИТЕ ИМЯ ДЛЯ ТАБЛИЦЫ РЕКОРДОВ");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        smallField = new JTextField(15);
        smallField.setToolTipText("ИМЯ ИГРОКА");
        smallField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Player.setName(smallField.getText());
                Level1State.getScoreTable().addRecord(Player.getCoinCounter());
                dispose();
            }
        });
        // Создание панели с текстовыми полями
        JPanel contents = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contents.add(smallField);
        setContentPane(contents);
        // Определяем размер окна и выводим его на экран
        setBounds(700, 500, 550, 130);
        setVisible(true);
    }
}
