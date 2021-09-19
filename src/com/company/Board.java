package com.company;

import javax.swing.*;
import java.awt.*;

public class Board extends JFrame {
    JLabel[][] label;

    public Board(int size, int generation, int[][] liveCellsLocation) {
        JPanel panel = new JPanel(new GridLayout(size,size));
        label = new JLabel[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                label[i][j] = new JLabel();
                label[i][j].setOpaque(true);
                label[i][j].setBackground(Color.WHITE);
                panel.add(label[i][j]);
            }
        }

        for (int i = 0; i < liveCellsLocation.length; i++) {
            for (int j = 0; j < liveCellsLocation[i].length; j++) {
                int column = liveCellsLocation[i][0];
                int row = liveCellsLocation[i][1];
                label[column][row].setBackground(Color.BLACK);
            }
        }

        add(panel, BorderLayout.CENTER);
    }
}
