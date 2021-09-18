package com.company;

import java.util.Arrays;

public class Main {
    public static void createConwayGame(int size) {
        // It's basically a grid, there may be an initial configuration like the grid size
        Boolean [][] conwayBoards = new Boolean[size][size];

        // How to start the data? Do I need to start in the middle of the boards?
        for (int i = 0; i < conwayBoards.length; i++) {
            System.out.println(Arrays.toString(conwayBoards[i]));
        }

        // no progress yet
    }

    public static void main(String[] args) {
        createConwayGame(4);
    }
}
