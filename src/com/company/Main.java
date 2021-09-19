package com.company;

import javax.swing.*;
import java.util.Arrays;

public class Main {
    public static int[][] createRandomLiveCellLocations(int amount, int boardSize) {
        int[][] liveCellsLocation = new int[amount][2];
        for (int i = 0; i < amount; i++) {
            int column = (int) (Math.random() * boardSize);
            int row = (int) (Math.random() * boardSize);
            liveCellsLocation[i][0] = column;
            liveCellsLocation[i][1] = row;
        }

        return liveCellsLocation;
    }

    public static int[][] createLiveCellPattern(String type) {
        if (type == "Glider") {
            int [][] patternCoordinates = {{0,1}, {1,2}, {2,0}, {2,1}, {2,2}};
            return patternCoordinates;
        }

        if (type == "Block") {
            int [][] patternCoordinates = {{1,1}, {1,2}, {2,1}, {2,2}};
            return patternCoordinates;
        }

        return null;
    }

    public static void main(String[] args) {
        int boardSize = 50;
        int initialLiveCell = 50;
//        int [][] liveCellLocations = createRandomLiveCellLocations(initialLiveCell, boardSize);
        int [][] liveCellLocations = createLiveCellPattern("Block");

        Board conwayBoard = new Board(boardSize, liveCellLocations);

        conwayBoard.setSize(400,400);
        conwayBoard.setVisible(true);
        conwayBoard.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }
}
