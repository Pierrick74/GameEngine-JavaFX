package org.TicTacToe.commun.RepresentationStrategy;

import org.TicTacToe.commun.Representation;

public class TicTacToeRepresentation implements RepresentationStrategy {
    public Representation getPlayerSymbol(int playerIndex) {
        return playerIndex == 0 ? Representation.ROUND : Representation.CROSS;
    }
    public Representation getEmptySymbol() { return Representation.EMPTY; }
}