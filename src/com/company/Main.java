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

    public static int[][] generateLiveCellPattern(String type, int boardSize) {
        int middleBoard = boardSize / 2;
        if (type == "Glider") {
            int [][] patternCoordinates = {{middleBoard,middleBoard+1}, {middleBoard+1,middleBoard+2}, {middleBoard+2,middleBoard}, {middleBoard+2,middleBoard+1}, {middleBoard+2,middleBoard+2}};
            return patternCoordinates;
        }

        if (type == "Block") {
            int [][] patternCoordinates = {{middleBoard+1,middleBoard+1}, {middleBoard+1,middleBoard+2}, {middleBoard+2,middleBoard+1}, {middleBoard+2,middleBoard+2}};
            return patternCoordinates;
        }

        return null;
    }

    public static void main(String[] args) {
        int boardSize = 100;
        int initialLiveCell = (int) (0.3 * (boardSize * boardSize));
        int [][] liveCellCoordinates = generateRandomLiveCellCoordinates(initialLiveCell, boardSize);
//        int [][] liveCellCoordinates = generateLiveCellPattern("Glider", boardSize);

        Board conwayBoard = new Board(boardSize, liveCellCoordinates);

        conwayBoard.setSize(400,400);
        conwayBoard.setVisible(true);
        conwayBoard.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }
}
