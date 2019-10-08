package com.company;

public abstract class SquareRole {

    protected  Square square = null;

    public SquareRole(Square s){
        assert s != null :  "Null square for square role";
        square = s;
    }

    public boolean isOccupied(){
        return square.getPlayer() != null;
    }

    public boolean isFirstSquare(){
        return false;
    }

    public boolean isLastSquare(){
        return false;
    }

    public Square moveAndLand(int moves){
        int lastPosition = square.findLastSquare().getPosition();
        int presentPosition = square.getPosition();
        if(presentPosition + moves > lastPosition){
            System.out.println("Should go to "
                                + (presentPosition + moves + 1)
                                + " beyond last square " + (lastPosition + 1)
                                + " so, don't move..");
            return square;
        } else {
            System.out.println("move from "
                                + (square.getPosition() + 1)  + " to "
                                + (square.findRelativeSquare(moves).getPosition()+1));
            return square.findRelativeSquare(moves).landHereOrGoHome();
        }
    }

    public Square landHereOrGoHome(){
        if(square.isOccupied()){
            System.out.println("square " + (square.getPosition() + 1) + " is occupied");
        } else {
            System.out.println("land at " + (square.getPosition() + 1) );
        }
        return square.isOccupied() ? square.findFirstSquare() : square;
    }

    public void enter(Player player){
        square.setPlayer(player);
        player.setSquare(square);
    }

    public void leave(Player player){
        square.setPlayer(null);
    }
}
