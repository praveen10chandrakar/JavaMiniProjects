package com.DSApplications.SnakesAndLadders;

import java.util.LinkedList;

public final class Game {
    private LinkedList<Player> players = new LinkedList<Player>();
    private Board board = null;
    private Player winner;

    private final class Die{
        private static final int MINVALUE = 1;
        private static final int  MAXVALUE = 2;

        public int roll(){
            return random(MINVALUE, MAXVALUE);
        }

        private int random(int min, int max){
            assert min < max;
            return (int) (min + Math.round((max - min) * Math.random()));
        }
    }

    private void movePlayer(int roll){
        Player currentPayer = players.remove();
        currentPayer.moveForward(roll);
        players.add(currentPayer);
        if (currentPayer.wins()){
            winner = currentPayer;
        }
    }

    public Game(String [] playerNames, int numSquares, int [][] snakes, int [][] ladders){
        makeBoard(numSquares, snakes, ladders);
        makePlayers(playerNames);
    }

    private void makeBoard(int numSquares, int [][] snakes, int [][] ladders){
        board = new Board(numSquares, ladders, snakes);
    }

    private void makePlayers(String [] playerNames){
        assert playerNames.length > 0 : "There must be some player";
        System.out.println("Players are: ");
        int i = 1;
        for (String name: playerNames){
            Player player = new Player(name);
            players.add(player);
            System.out.println(i + ". " + name);
            i++;
        }
    }

    public void play(){
        assert !players.isEmpty(): "No players to play";
        assert board != null : "No scoreboard to play";

        Die die = new Die();
        startGame();
        System.out.println("Initial State: \n" + this);

        while (notOver()){
            int roll = die.roll();
            System.out.println("Current player is: " + currentPlayer() + " and rolls " + roll );
            movePlayer(roll);
            System.out.println("State is: \n" + this);
        }

        System.out.println(winner + " has won...!!");
    }

    private void startGame(){
        placePlayersAtFirstSquare();
        winner = null;
    }

    private void placePlayersAtFirstSquare(){
        for (Player player : players){
            board.firstSquare().enter(player);
        }
    }

    private boolean notOver(){
        return winner == null;
    }

    @Override
    public String toString() {
        String string = new String();
        for (Player player: players){
            string += player.getName() + " is at square " + (player.position() + 1) + "\n";
        }
        return string;
    }

    Player currentPlayer(){
        assert players.size() > 0;
        return players.peek();
    }
}
