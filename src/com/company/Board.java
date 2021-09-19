package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.Timer;

public class Board extends JFrame {
    JLabel[][] label;
    JLabel[][] label2;

    public Board(int size, int generation, int[][] liveCellsLocation) {
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

        for (int i = 0; i < liveCellsLocation.length; i++) {
            for (int j = 0; j < liveCellsLocation[i].length; j++) {
                int column = liveCellsLocation[i][0];
                int row = liveCellsLocation[i][1];
                label[column][row].setBackground(Color.BLACK);
            }
        }

        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (panel.isShowing()) {
                    for (int i = 0; i < size; i++) {
                        for (int j = 0; j < size; j++) {
                            if (label[i][j].getBackground() == Color.BLACK) label2[i][j].setBackground(Color.BLACK);
                            else label2[i][j].setBackground(Color.WHITE);
                        }
                    }

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
                    for (int i = 0; i < size; i++) {
                        for (int j = 0; j < size; j++) {
                            if (label2[i][j].getBackground() == Color.BLACK) label[i][j].setBackground(Color.BLACK);
                            else label[i][j].setBackground(Color.WHITE);
                        }
                    }

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
            }
        });

        add(panel, BorderLayout.CENTER);

        timer.setRepeats(true);
        timer.start();



//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//                label[i][j].setOpaque(true);
//                label[i][j].setBackground(Color.WHITE);
//            }
//        }

//        remove(panel);
//        add(panel2, BorderLayout.CENTER);
//        revalidate();
    }

    public static int countLiveNeighbourCells(int i, int j, JLabel[][] boards) {
        int counter = 0;
        int [][] locationAroundTheCell = {
                {i-1, j-1}, {i-1, j}, {i-1, j+1},
                {i,   j-1}, /* [] */  {i,   j+1},
                {i+1, j-1}, {i+1, j}, {i+1, j+1}
        };
        for (int k = 0; k < locationAroundTheCell.length; k++) {
            int column = locationAroundTheCell[k][0];
            int row = locationAroundTheCell[k][1];
            try {
                if (boards[column][row].getBackground() == Color.BLACK) counter++;
            } catch (Exception e) {

            }
        }
        return counter;
    }
}
