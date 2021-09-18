package com.company;

import java.util.Arrays;

public class Main {
    public static void createConwayGame(int size) {
        // It's basically a grid, there may be an initial configuration like the grid size
        String [][] conwayBoards = new String[size][size];

        // How to start the data? Do I need to start in the middle of the boards?

        // Set up the initial values for the boards
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                conwayBoards[i][j] = "#";
            }
        }

        for (int i = (size / 2) - 1; i < (size / 2) + 1 ; i++) {
            for (int j = (size / 2) - 1; j < (size / 2) + 1; j++) {
                conwayBoards[i][j] = "O";
            }
        }

        // Visualize the boards
        for (int i = 0; i < size; i++) {
            System.out.println(Arrays.toString(conwayBoards[i]));
        }

        System.out.println();

        // is the boards updated as a whole or its being checked on every cell and get updated?
        for (int k = 0; k < 3; k++) {
            String [][] tempBoards = new String[size][size];

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    tempBoards[i][j] = conwayBoards[i][j];
                }
            }

            for (int i = (size / 2) - 2; i < (size / 2) + 2; i++) {
                for (int j = 0; j < size; j++) {
                    int liveCells = countLiveNeighbourCells(i, j, conwayBoards);
                    if (liveCells < 2 || liveCells > 3) tempBoards[i][j] = "#";
                    else tempBoards[i][j] = "O";
                }
            }
            for (int i = 0; i < size; i++) {
                System.out.println(Arrays.toString(tempBoards[i]));
            }
            System.out.println();

            conwayBoards = tempBoards;
        }
    }

    public static int countLiveNeighbourCells(int i, int j, String[][] boards) {
        int counter = 0;
        try {
            if (boards[i-1][j-1] == "O") counter++;
            if (boards[i-1][j] == "O") counter++;
            if (boards[i-1][j+1] == "O") counter++;
            if (boards[i][j-1] == "O") counter++;
            if (boards[i][j+1] == "O") counter++;
            if (boards[i+1][j-1] == "O") counter++;
            if (boards[i+1][j] == "O") counter++;
            if (boards[i+1][j+1] == "O") counter++;
        } catch (Exception e) {}
        return counter;
    }

    public static void main(String[] args) {
        createConwayGame(5);
    }
}
