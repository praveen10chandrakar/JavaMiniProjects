package com.company;

public final class LadderRole extends SquareRole{

    private int transport;

    public LadderRole(Square s, int t){
        super(s);
        assert t > 0 : "A ladder shift must be positive";
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
