package com.company;

import java.util.ArrayList;

public class Board {
    private ArrayList <Square> squares = new ArrayList<>();
    private static int MIN_SQUARES = 10;

    public Board(int numSquares, int [][] ladders, int [][] snakes){
        assert numSquares > MIN_SQUARES : "There must be atleast " + MIN_SQUARES + " squares";
        makeSquares(numSquares);
        makeLadders(ladders);
        makeSnakes(snakes);
    }

    private void makeSquares(int numSquares){
        System.out.println("There are " + numSquares + " squares");
        for(int position = 0; position < numSquares; position++){
            Square square = new Square(position, this);
            squares.add(square);
            square.setSquareRole(new RegularSquareRole(square));
        }
        firstSquare().setSquareRole(new FirstSquareRole(firstSquare()));
        lastSquare().setSquareRole(new LastSquareRole(lastSquare()));
    }

    public Square firstSquare(){
        return squares.get(0);
    }

    public Square lastSquare(){
        return squares.get(squares.size() - 1);
    }

    public Square findSquare(int position){
        assert ((position > 0) && (position < numberOfSquares())) : " inexistent square";
        return squares.get(position);
    }

    private int numberOfSquares(){
        assert  !squares.isEmpty();
        return squares.size();
    }

    private void makeSnakes(int [][] snakes){
        for (int i = 0; i < snakes.length; i++){
            assert snakes[i].length == 2;

            int fromPosition = snakes[i][0] - 1;
            int toPosition = snakes[i][1] - 1;

            int transport = toPosition - fromPosition;

            assert  transport < 0 : "In snake, destination after origin";
            assert (toPosition > 0 && toPosition < numberOfSquares() - 1);
            assert (fromPosition > 0 && fromPosition < numberOfSquares() - 1);

            System.out.println("Snake from " + (fromPosition + 1) + " to position " + (toPosition + 1) );


            Square snakeSquare = squares.get(fromPosition);
            snakeSquare.setSquareRole(new SnakeRole(snakeSquare, transport));
        }
    }

    private void makeLadders(int [][] ladders){
        for (int i = 0; i < ladders.length; i++){
            assert ladders[i].length == 2;

            int fromPosition = ladders[i][0] - 1;
            int toPosition = ladders[i][1] - 1;

            int transport = toPosition - fromPosition;

            assert  transport < 0 : "In snake, destination after origin";
            assert (toPosition > 0 && toPosition < numberOfSquares() - 1);
            assert (fromPosition > 0 && fromPosition < numberOfSquares() - 1);

            System.out.println("Ladder from " + (fromPosition + 1) + " to position " + (toPosition + 1) );


            Square ladderSquare = squares.get(fromPosition);
            ladderSquare.setSquareRole(new LadderRole(ladderSquare, transport));
        }
    }
}
