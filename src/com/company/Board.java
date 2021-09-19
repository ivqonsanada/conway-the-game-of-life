package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Board extends JFrame {
    JLabel[][] label;
    JLabel[][] label2;

    public Board(int size, int[][] liveCellCoordinates) {
        JPanel panel = new JPanel(new GridLayout(size, size));
        JPanel panel2 = new JPanel(new GridLayout(size, size));

        label = new JLabel[size][size];
        label2 = new JLabel[size][size];

        setDefaultPanelConfiguration(panel, label);
        setDefaultPanelConfiguration(panel2, label2);

        for (int[] coordinate : liveCellCoordinates) {
            int y = coordinate[0];
            int x = coordinate[1];
            label[y][x].setBackground(Color.BLACK);
            label2[y][x].setBackground(Color.BLACK);
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
        int [][] neighbourCoordinates = {
                {i-1, j-1}, {i-1, j}, {i-1, j+1},
                {i,   j-1}, /* [] */  {i,   j+1},
                {i+1, j-1}, {i+1, j}, {i+1, j+1}
        };
        for (int[] coordinate : neighbourCoordinates) {
            int x = coordinate[1];
            int y = coordinate[0];
            try {
                if (boards[y][x].getBackground() == Color.BLACK) counter++;
            } catch (Exception ignored) {

            }
        }
        return counter;
    }
}
