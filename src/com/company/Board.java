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

        setDefaultPanelConfig(firstPanel, label);
        setDefaultPanelConfig(secondPanel, label2);

        for (int[] coordinate : liveCellCoordinates) {
            int y = coordinate[0];
            int x = coordinate[1];
            label[y][x].setBackground(Color.BLACK);
            label2[y][x].setBackground(Color.BLACK);
        }

        add(firstPanel, BorderLayout.CENTER);

        Timer timer = new Timer(2000, event -> {
            if (firstPanel.isDisplayable()) {
                for (int y = 0; y < size; y++) {
                    for (int x = 0; x < size; x++) {
                        boolean isCurrentCellAlive = label[y][x].getBackground() == Color.BLACK;
                        int liveNeighbourCells = countLiveNeighbourCells(y, x, label);
                        if (isCurrentCellAlive && (liveNeighbourCells < 2 || liveNeighbourCells > 3)) label2[y][x].setBackground(Color.WHITE);
                        else if (!isCurrentCellAlive && liveNeighbourCells == 3) label2[y][x].setBackground(Color.BLACK);
                        else if (isCurrentCellAlive) label2[y][x].setBackground(Color.BLACK);
                    }
                }

                remove(firstPanel);
                add(secondPanel, BorderLayout.CENTER);
            } else if (secondPanel.isShowing()) {
                for (int y = 0; y < size; y++) {
                    for (int x = 0; x < size; x++) {
                        boolean isCurrentCellAlive = label2[y][x].getBackground() == Color.BLACK;
                        int liveNeighbourCells = countLiveNeighbourCells(y, x, label2);
                        if (isCurrentCellAlive && (liveNeighbourCells < 2 || liveNeighbourCells > 3)) label[y][x].setBackground(Color.WHITE);
                        else if (!isCurrentCellAlive && liveNeighbourCells == 3) label[y][x].setBackground(Color.BLACK);
                        else if (isCurrentCellAlive) label[y][x].setBackground(Color.BLACK);
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
        for (int y = 0; y < label.length; y++) {
            for (int x = 0; x < label.length; x++) {
                label[y][x] = new JLabel();
                label[y][x].setOpaque(true);
                label[y][x].setBackground(color);
                panel.add(label[y][x]);
            }
        }
    }

    public static void setDefaultPanelConfig(JPanel panel, JLabel[][] label) {
        setPanelLabelBg(panel, label, Color.WHITE);
    }

    public static int countLiveNeighbourCells(int y, int x, JLabel[][] label) {
        int counter = 0;
        int [][] neighbourCoordinates = {
                {y-1, x-1}, {y-1, x}, {y-1, x+1},
                {y,   x-1}, /* [] */  {y,   x+1},
                {y+1, x-1}, {y+1, x}, {y+1, x+1}
        };
        for (int[] coordinate : neighbourCoordinates) {
            int X = coordinate[1];
            int Y = coordinate[0];
            try {
                if (label[Y][X].getBackground() == Color.BLACK) counter++;
            } catch (Exception ignored) {

            }
        }
        return counter;
    }
}
