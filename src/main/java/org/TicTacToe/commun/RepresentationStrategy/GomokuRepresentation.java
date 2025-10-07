package org.TicTacToe.commun.RepresentationStrategy;

import org.TicTacToe.commun.Representation;

public class GomokuRepresentation implements RepresentationStrategy {
    public Representation getPlayerSymbol(int playerIndex) {
        return playerIndex == 0 ? Representation.BLACK : Representation.WHITE;
    }
    public Representation getEmptySymbol() { return Representation.EMPTY; }
}
