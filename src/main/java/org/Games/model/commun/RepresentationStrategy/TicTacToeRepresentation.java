package org.Games.model.commun.RepresentationStrategy;

import org.Games.model.player.Representation;

public class TicTacToeRepresentation implements RepresentationStrategy {
    public Representation getPlayerSymbol(int playerIndex) {
        return playerIndex == 0 ? Representation.ROUND : Representation.CROSS;
    }
}