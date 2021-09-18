package com.company;

import java.util.Arrays;

public class Main {
    public static void createConwayGame(int size) {
        // It's basically a grid, there may be an initial configuration like the grid size
        Boolean [][] conwayBoards = new Boolean[size][size];

        // How to start the data? Do I need to start in the middle of the boards?

        // Set up the initial values for the boards
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                conwayBoards[i][j] = false;
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
