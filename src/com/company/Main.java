package com.company;

import java.util.Arrays;

public class Main {
    public static void createConwayGame(int size, int generation, int[][] liveCellsLocation) {
        // It's basically a grid, there may be an initial configuration like the grid size
        String[][] conwayBoards = new String[size][size];

        // How to start the data? Do I need to start in the middle of the boards?
        // Do I need to create an array consist of random live cells?

        // Set up the initial values for the boards
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                conwayBoards[i][j] = "#";
            }
        }

        for (int i = 0; i < liveCellsLocation.length; i++) {
            for (int j = 0; j < liveCellsLocation[i].length; j++) {
                int column = liveCellsLocation[i][0];
                int row = liveCellsLocation[i][1];
                conwayBoards[column][row] = "O";
            }
        }

        // Visualize the boards
        for (int i = 0; i < size; i++) {
            System.out.println(Arrays.toString(conwayBoards[i]));
        }

        System.out.println();

        // is the board updated as a whole or its being checked on every cell and get updated?
        for (int k = 0; k < generation; k++) {
            String[][] tempBoards = new String[size][size];

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

            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }

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

    public static void main(String[] args) {
        int boardSize = 5;
        int generation = 2;
        int initialLiveCell = 10;
        int [][] liveCellLocations = createRandomLiveCellLocations(initialLiveCell, boardSize);

        createConwayGame(boardSize, generation, liveCellLocations);
    }
}
