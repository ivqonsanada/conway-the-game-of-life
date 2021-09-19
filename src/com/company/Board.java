package com.company;

import javax.swing.*;
import java.awt.*;

public class Board extends JFrame {
    JLabel[][] label;

    public Board() {
        JPanel panel = new JPanel(new GridLayout(8,8));
        label = new JLabel[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                label[i][j] = new JLabel();
                label[i][j].setOpaque(true);
                panel.add(label[i][j]);
            }
        }
        add(panel, BorderLayout.CENTER);
    }

    public void paint(Graphics g) {
        for (int x = 30; x <= 300; x += 30)
            for (int y = 30; y <= 300; y += 30)
                g.drawRect(x, y, 30, 30);

    }

}
