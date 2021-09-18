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
                conwayBoards[i][j] = "dead";
            }
        }

        for (int i = (size / 2) - 1; i < (size / 2) + 1 ; i++) {
            for (int j = (size / 2) - 1; j < (size / 2) + 1; j++) {
                conwayBoards[i][j] = "live";
            }
        }

        // Visualize the boards
        for (int i = 0; i < size; i++) {
            System.out.println(Arrays.toString(conwayBoards[i]));
        }
    }

    public static void main(String[] args) {
        createConwayGame(4);
    }
}
