package org.TicTacToe.commun.PlacementStrategy;

import org.TicTacToe.board.Board;
import org.TicTacToe.commun.Coordinate;
import org.TicTacToe.player.Player;

import java.util.List;

public interface PlacementStrategie {
    List<Coordinate> getAvailableMove(Board board);
    boolean isValideCoordinate(Board board, Coordinate coordinate);
    Coordinate setCell(Board board, Coordinate coordinate, Player player);
}
