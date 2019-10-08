package com.DSApplications.SnakesAndLadders;

import java.util.ArrayList;

public final class FirstSquareRole extends SquareRole {
    private ArrayList <Player > players = new ArrayList <Player >();

    public FirstSquareRole(Square s){
        super(s);
    }

    @Override
    public boolean isOccupied() {
        return !players.isEmpty();
    }

    @Override
    public boolean isFirstSquare() {
        return true;
    }

    @Override
    public void enter(Player player) {
        players.add(player);
        player.setSquare(square);
    }

    @Override
    public void leave(Player player) {
        players.remove(player);
    }
}
