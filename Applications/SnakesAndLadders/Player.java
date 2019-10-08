package com.company;

public class Player {
    private Square square = null;
    private String name;

    public Square getSquare() {
        return square;
    }
     public void setSquare(Square square){
        this.square = square;
     }

     public int position(){
        return square.getPosition();
     }

    public String getName() {
        return name;
    }

    public Player(String name){
        this.name = name;
    }

    public String toString(){
        return name;
    }

    public boolean wins(){
        return square.isLastSquare();
    }

    public void moveForward(int moves){
        assert  moves > 0 : "non-positive moves";
        square.leave(this);
        square = square.moveAndLand(moves);
        square.enter(this);
    }
}
