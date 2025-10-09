package org.TicTacToe.commun.PlacementStrategy;

import org.TicTacToe.board.Board;
import org.TicTacToe.commun.Coordinate;
import org.TicTacToe.commun.Exception.OutOfBoardException;
import org.TicTacToe.commun.Representation;
import org.TicTacToe.interaction.Display;
import org.TicTacToe.interaction.Terminal;
import org.TicTacToe.player.Player;

import java.util.ArrayList;
import java.util.List;

public class FreePlacement implements PlacementStrategie {

    public boolean isValideCoordinate(Board board, Coordinate coordinate) {
        return board.isEmptyCase(coordinate);
    }

    public List<Coordinate> getAvailableMove(Board board){
        List<Coordinate> coordinates = new ArrayList<Coordinate>();
        for (int i = 0; i < board.getXSize(); i++) {
            for (int j = 0; j < board.getYSize(); j++) {
                if (board.getCell(new Coordinate(i, j)).getType() == Representation.EMPTY) {
                    coordinates.add(new Coordinate(i, j));
                }
            }
        }
        return coordinates;
    }

    public Coordinate setCell(Board board, Coordinate coordinate, Player player){
        board.setCell(coordinate, player);
        return coordinate;
    }
}
