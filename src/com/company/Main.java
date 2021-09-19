package com.company;

import javax.swing.*;

public class Main {
    public static int[][] generateRandomLiveCellCoordinates(int amount, int boardSize) {
        int[][] liveCellCoordinates = new int[amount][2];
        for (int i = 0; i < amount; i++) {
            int y = (int) (Math.random() * boardSize);
            int x = (int) (Math.random() * boardSize);
            liveCellCoordinates[i][0] = y;
            liveCellCoordinates[i][1] = x;
        }

        return liveCellCoordinates;
    }

    public static void main(String[] args) {
        int boardSize = 50;
        int initialLiveCell = (int) (0.15 * (boardSize * boardSize));
        int [][] liveCellCoordinates = generateRandomLiveCellCoordinates(initialLiveCell, boardSize);

        Board conwayBoard = new Board(boardSize, liveCellCoordinates);

        conwayBoard.setSize(400,400);
        conwayBoard.setVisible(true);
        conwayBoard.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }
}
