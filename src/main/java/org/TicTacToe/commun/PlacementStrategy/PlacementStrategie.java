package org.TicTacToe.commun.PlacementStrategy;

import org.TicTacToe.board.Board;
import org.TicTacToe.commun.Coordinate;

import java.util.List;

public interface PlacementStrategie {
    Coordinate askForPlacement(Board board);
    public List<Coordinate> getAvailableMove(Board board);
}
