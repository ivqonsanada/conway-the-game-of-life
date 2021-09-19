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

        setDefaultPanelConfiguration(panel, label);
        setDefaultPanelConfiguration(panel2, label2);

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

        add(panel, BorderLayout.CENTER);

        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (panel.isShowing()) {
                    // just realize this method only check the middle board, it was because I only try to avoid array out of bound exception
                    for (int i = 0; i < size; i++) {
                        for (int j = 0; j < size; j++) {
                            Boolean isCurrentCellAlive = label[i][j].getBackground() == Color.BLACK;
                            int liveNeighbourCells = countLiveNeighbourCells(i, j, label);
                            if (isCurrentCellAlive && (liveNeighbourCells < 2 || liveNeighbourCells > 3)) label2[i][j].setBackground(Color.WHITE);
                            if (!isCurrentCellAlive && liveNeighbourCells == 3) label2[i][j].setBackground(Color.BLACK);
                        }
                    }

                    remove(panel);
                    add(panel2, BorderLayout.CENTER);
                } else if (panel2.isShowing()) {
                    for (int i = 0; i < size; i++) {
                        for (int j = 0; j < size; j++) {
                            int liveCells = countLiveNeighbourCells(i, j, label2);
                            Boolean isCurrentCellAlive = label2[i][j].getBackground() == Color.BLACK;
                            if (isCurrentCellAlive && (liveCells < 2 || liveCells > 3)) label[i][j].setBackground(Color.WHITE);
                            if (!isCurrentCellAlive && liveCells == 3) label[i][j].setBackground(Color.BLACK);
                        }
                    }

                    remove(panel2);
                    add(panel, BorderLayout.CENTER);
                }

                revalidate();
                repaint();
            }
        });

        timer.setRepeats(true);
        timer.start();
    }

    public static void setPanelLabelBg(JPanel panel, JLabel[][] label, Color color) {
        for (int i = 0; i < label.length; i++) {
            for (int j = 0; j < label.length; j++) {
                label[i][j] = new JLabel();
                label[i][j].setOpaque(true);
                label[i][j].setBackground(color);
                panel.add(label[i][j]);
            }
        }
    }

    public static void setDefaultPanelConfiguration(JPanel panel, JLabel[][] label) {
        setPanelLabelBg(panel, label, Color.WHITE);
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
