package org.Games.model.rules.PlacementStrategy;

import org.Games.model.board.Board;
import org.Games.model.board.Coordinate;
import org.Games.model.player.Player;

import java.util.List;

public interface PlacementStrategie {
    List<Coordinate> getAvailableMove(Board board);
    boolean isValideCoordinate(Board board, Coordinate coordinate);
    Coordinate setCell(Board board, Coordinate coordinate, Player player);
}
