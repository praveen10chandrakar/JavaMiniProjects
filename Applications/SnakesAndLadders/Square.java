package com.company;

public class Square {
    private Board board = null;
    private Player player = null;
    private int position;
    private SquareRole squareRole = null;

    public Square(int position, Board board){
        assert position > 0 : "Square number must be positive";
        this.position = position;
        this.board = board;
    }

    public Player getPlayer(){
        return player;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public int getPosition() {
        return position;
    }

    public boolean isLastSquare (){
        return squareRole.isLastSquare();
    }

    public void setSquareRole(SquareRole squareRole){
        assert squareRole != null;
        this.squareRole = squareRole;
    }

    public boolean isOccupied(){
        return squareRole.isOccupied();
    }

    public Square moveAndLand(int moves){
        return squareRole.moveAndLand(moves);
    }

    public Square landHereOrGoHome(){
        return squareRole.landHereOrGoHome();
    }

    public void enter(Player p){
        squareRole.enter(p);
    }

    public void leave(Player p){
        squareRole.leave(p);
    }

    public Square findRelativeSquare(int shift){
        return board.findSquare(position + shift);
    }

    public Square findFirstSquare(){
        return board.firstSquare();
    }

    public Square findLastSquare(){
        return board.lastSquare();
    }
}
