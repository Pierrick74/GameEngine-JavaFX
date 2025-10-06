package org.TicTacToe.board;

import org.TicTacToe.Player;
import org.TicTacToe.commun.Representation;

public class Cell {
    Player player;

    public Cell() {
        this.player = null;
    }

    public Cell(Player player) {
        this.player = player;
    }

    public String toString() {
        if( this.player == null ) {
            return "   ";
        } else {
            return " " + player.toString() + " ";
        }
    }

    public Representation getType() {
        if (player == null) {
            return Representation.EMPTY;
        }
        return player.getType();
    }
}
