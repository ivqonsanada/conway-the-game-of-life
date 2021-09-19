package com.company;

import javax.swing.*;
import java.awt.*;
import javax.swing.Timer;

public class Board extends JFrame {
    public Board(int size, int[][] liveCellCoordinates) {
        JPanel firstPanel = new JPanel(new GridLayout(size, size));
        JPanel secondPanel = new JPanel(new GridLayout(size, size));

        JLabel[][] label = new JLabel[size][size];
        JLabel[][] label2 = new JLabel[size][size];

        setDefaultPanelConfiguration(firstPanel, label);
        setDefaultPanelConfiguration(secondPanel, label2);

        for (int[] coordinate : liveCellCoordinates) {
            int y = coordinate[0];
            int x = coordinate[1];
            label[y][x].setBackground(Color.BLACK);
            label2[y][x].setBackground(Color.BLACK);
        }

        add(firstPanel, BorderLayout.CENTER);

        Timer timer = new Timer(100, event -> {
            if (firstPanel.isShowing()) {
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        boolean isCurrentCellAlive = label[i][j].getBackground() == Color.BLACK;
                        int liveNeighbourCells = countLiveNeighbourCells(i, j, label);
                        if (isCurrentCellAlive && (liveNeighbourCells < 2 || liveNeighbourCells > 3)) label2[i][j].setBackground(Color.WHITE);
                        else if (!isCurrentCellAlive && liveNeighbourCells == 3) label2[i][j].setBackground(Color.BLACK);
                        else if (isCurrentCellAlive && (liveNeighbourCells == 2 || liveNeighbourCells == 3)) label2[i][j].setBackground(Color.BLACK);
                    }
                }

                remove(firstPanel);
                add(secondPanel, BorderLayout.CENTER);
            } else if (secondPanel.isShowing()) {
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        boolean isCurrentCellAlive = label2[i][j].getBackground() == Color.BLACK;
                        System.out.println(isCurrentCellAlive);
                        int liveNeighbourCells = countLiveNeighbourCells(i, j, label2);
                        if (isCurrentCellAlive && (liveNeighbourCells < 2 || liveNeighbourCells > 3)) label[i][j].setBackground(Color.WHITE);
                        else if (!isCurrentCellAlive && liveNeighbourCells == 3) label[i][j].setBackground(Color.BLACK);
                        else if (isCurrentCellAlive && (liveNeighbourCells == 2 || liveNeighbourCells == 3)) label[i][j].setBackground(Color.BLACK);
                    }
                }

                remove(secondPanel);
                add(firstPanel, BorderLayout.CENTER);
            }

            revalidate();
            repaint();
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

    public static int countLiveNeighbourCells(int i, int j, JLabel[][] label) {
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
                if (label[y][x].getBackground() == Color.BLACK) counter++;
            } catch (Exception ignored) {

            }
        }
        return counter;
    }
}
