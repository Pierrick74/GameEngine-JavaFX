package org.TicTacToe.commun.RepresentationStrategy;

import org.TicTacToe.commun.Representation;

public class Power4Representation implements RepresentationStrategy {
    public Representation getPlayerSymbol(int playerIndex) {
        return playerIndex == 0 ? Representation.YELLOW : Representation.RED;
    }
    public Representation getEmptySymbol() { return Representation.EMPTY; }
}
