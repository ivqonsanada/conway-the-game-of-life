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

    public static void main(String[] args) {
        int boardSize = 50;
        int initialLiveCell = 50;
        int [][] liveCellLocations = createRandomLiveCellLocations(initialLiveCell, boardSize);

        Board conwayBoard = new Board(boardSize, liveCellLocations);

        conwayBoard.setSize(400,400);
        conwayBoard.setVisible(true);
        conwayBoard.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }
}
