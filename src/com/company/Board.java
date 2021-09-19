package com.company;

import javax.swing.*;
import java.awt.*;

public class Board extends JFrame {
    JLabel[][] label;

    public Board(int size, int generation, int[][] liveCellsLocation) {
        JPanel panel = new JPanel(new GridLayout(size, size));
        JPanel panel2 = new JPanel(new GridLayout(size, size));

        label = new JLabel[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                label[i][j] = new JLabel();
                label[i][j].setOpaque(true);
                label[i][j].setBackground(Color.WHITE);
                panel.add(label[i][j]);
                panel2.add(label[i][j]);
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

    public static int countLiveNeighbourCells(int i, int j, String[][] boards) {
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
                if (boards[column][row] == "O") counter++;
            } catch (Exception e) {

            }
        }
        return counter;
    }
}
