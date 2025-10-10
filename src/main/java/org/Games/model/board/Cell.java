package org.Games.model.board;

import org.Games.model.player.Player;
import org.Games.model.player.Representation;

import java.io.Serializable;

public class Cell implements Serializable {
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
            return " " + player + " ";
        }
    }

    public Representation getType() {
        if (player == null) {
            return Representation.EMPTY;
        }
        return player.getType();
    }
}
