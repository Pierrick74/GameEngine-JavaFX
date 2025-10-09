package org.Games.model.commun.RepresentationStrategy;

import org.Games.model.player.Representation;

public class Power4Representation implements RepresentationStrategy {
    public Representation getPlayerSymbol(int playerIndex) {
        return playerIndex == 0 ? Representation.YELLOW : Representation.RED;
    }
    public Representation getEmptySymbol() { return Representation.EMPTY; }
}
