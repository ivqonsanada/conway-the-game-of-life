package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Board extends JFrame {
    JLabel[][] label;
    JLabel[][] label2;

    public Board(int size, int[][] liveCellsLocation) {
        JPanel panel = new JPanel(new GridLayout(size, size));
        JPanel panel2 = new JPanel(new GridLayout(size, size));

        label = new JLabel[size][size];
        label2 = new JLabel[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                label[i][j] = new JLabel();
                label[i][j].setOpaque(true);
                label[i][j].setBackground(Color.WHITE);
                panel.add(label[i][j]);

                label2[i][j] = new JLabel();
                label2[i][j].setOpaque(true);
                label2[i][j].setBackground(Color.WHITE);
                panel2.add(label2[i][j]);
            }
        }

        for (int[] ints : liveCellsLocation) {
            for (int j = 0; j < ints.length; j++) {
                int column = ints[0];
                int row = ints[1];
                label[column][row].setBackground(Color.BLACK);
            }

            for (int j = 0; j < ints.length; j++) {
                int column = ints[0];
                int row = ints[1];
                label2[column][row].setBackground(Color.BLACK);
            }
        }

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (panel.isShowing()) {
//                    for (int i = 0; i < size; i++) {
//                        for (int j = 0; j < size; j++) {
//                            if (label[i][j].getBackground() == Color.BLACK) label2[i][j].setBackground(Color.BLACK);
//                            else label2[i][j].setBackground(Color.WHITE);
//                        }
//                    }

                    // just realize this method only check the middle board, it was because I only try to avoid array out of bound exception
                    for (int i = 0; i < size; i++) {
                        for (int j = 0; j < size; j++) {
                            int liveCells = countLiveNeighbourCells(i, j, label);
                            if (liveCells < 2 || liveCells > 3) label2[i][j].setBackground(Color.WHITE);
                            else label2[i][j].setBackground(Color.BLACK);
                        }
                    }

                    remove(panel);
                    add(panel2, BorderLayout.CENTER);
                } else {
//                    for (int i = 0; i < size; i++) {
//                        for (int j = 0; j < size; j++) {
//                            if (label2[i][j].getBackground() == Color.BLACK) label[i][j].setBackground(Color.BLACK);
//                            else label[i][j].setBackground(Color.WHITE);
//                        }
//                    }

                    for (int i = 0; i < size; i++) {
                        for (int j = 0; j < size; j++) {
                            int liveCells = countLiveNeighbourCells(i, j, label2);
                            if (liveCells < 2 || liveCells > 3) label[i][j].setBackground(Color.WHITE);
                            else label[i][j].setBackground(Color.BLACK);
                        }
                    }

                    remove(panel2);
                    add(panel, BorderLayout.CENTER);
                }

                revalidate();
                repaint();
            }
        });

        add(panel, BorderLayout.CENTER);

        timer.setRepeats(true);
        timer.start();
    }

    public static int countLiveNeighbourCells(int i, int j, JLabel[][] boards) {
        int counter = 0;
        int [][] locationAroundTheCell = {
                {i-1, j-1}, {i-1, j}, {i-1, j+1},
                {i,   j-1}, /* [] */  {i,   j+1},
                {i+1, j-1}, {i+1, j}, {i+1, j+1}
        };
        for (int[] ints : locationAroundTheCell) {
            int column = ints[0];
            int row = ints[1];
            try {
                if (boards[column][row].getBackground() == Color.BLACK) counter++;
            } catch (Exception ignored) {

            }
        }
        return counter;
    }
}
