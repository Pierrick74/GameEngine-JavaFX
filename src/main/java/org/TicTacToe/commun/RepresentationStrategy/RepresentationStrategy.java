package org.TicTacToe.commun.RepresentationStrategy;
import org.TicTacToe.commun.Representation;

public interface RepresentationStrategy {
    Representation getPlayerSymbol(int playerIndex);
}
