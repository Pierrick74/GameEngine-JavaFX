package org.Games.model.commun.RepresentationStrategy;

import org.Games.model.player.Representation;

import java.io.Serializable;

public class GomokuRepresentation implements RepresentationStrategy, Serializable {
    public Representation getPlayerSymbol(int playerIndex) {
        return playerIndex == 0 ? Representation.BLACK : Representation.WHITE;
    }
    public Representation getEmptySymbol() { return Representation.EMPTY; }
}
