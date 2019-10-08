package com.DSApplications.SnakesAndLadders;

public final class SnakeRole extends SquareRole {
    private int transport;
    public SnakeRole(Square s, int t){
        super(s);
        transport = t;
    }

    @Override
    public Square landHereOrGoHome() {
        System . out. println (" ladder from " + ( square . getPosition ()+1)
                               + " to " + ( destination (). getPosition ()+1));
        return destination().landHereOrGoHome();
    }

    private Square destination(){
        return square.findRelativeSquare(transport);
    }
}
