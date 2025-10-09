package org.Games.model.commun.RepresentationStrategy;
import org.Games.model.player.Representation;

public interface RepresentationStrategy {
    Representation getPlayerSymbol(int playerIndex);
}
