package com.DSApplications.SnakesAndLadders;

public class Main {

    public static void main(String[] args) {
        String playerNames[] = {"Praveen", "Bhanu", "Sanjeet", "Swati"};
        int numSquares = 120;

        int snakesFromTo[][] = {{11, 5}};
        int laddersFromTo [][] = {{2, 6}, {7, 9}};

        Game game = new Game(playerNames, numSquares, snakesFromTo, laddersFromTo);

        game.play();
    }
}
