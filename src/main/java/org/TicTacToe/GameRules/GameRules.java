package org.TicTacToe.GameRules;

import org.TicTacToe.board.Board;
import org.TicTacToe.commun.Coordinate;

public interface GameRules {
    Coordinate getAvailablePlacement(Board board);
}
