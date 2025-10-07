package org.TicTacToe.commun.PlacementStrategy;

import org.TicTacToe.board.Board;
import org.TicTacToe.commun.Coordinate;

public interface PlacementStrategie {
    Coordinate askForPlacement(Board board);
}
