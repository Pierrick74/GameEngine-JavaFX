package org.TicTacToe.commun.PlacementStrategy;

import org.TicTacToe.board.Board;
import org.TicTacToe.board.Cell;
import org.TicTacToe.commun.Coordinate;
import org.TicTacToe.commun.Exception.OutOfBoardException;
import org.TicTacToe.commun.Representation;
import org.TicTacToe.interaction.Display;
import org.TicTacToe.interaction.Terminal;
import org.TicTacToe.player.Player;

import java.util.ArrayList;
import java.util.List;

public class GravityPlacement implements PlacementStrategie{
    public boolean isValideCoordinate(Board board, Coordinate coordinate) {
        Cell[] verticalCells = board.getVerticalCells(coordinate);
        return verticalCells[0].getType() == Representation.EMPTY;

    }

    public Coordinate setCell(Board board, Coordinate coordinate, Player player) {
        int row = getRowAvailable(board, coordinate.getCol());
        Coordinate newCoordinate = new Coordinate(row, coordinate.getCol());
        board.setCell(newCoordinate, player);
        return newCoordinate;
    }

    private int getRowAvailable(Board board, int col) {
        Cell[] verticalCells = board.getVerticalCells(new Coordinate(0, col));

        for (int row = 0; row < verticalCells.length; row++){
            if (verticalCells[row].getType() != Representation.EMPTY){
                return row-1;
            }
        }
        return verticalCells.length-1;
    }

    public List<Coordinate> getAvailableMove(Board board){
        List<Coordinate> coordinates = new ArrayList<Coordinate>();

        for  (int i = 0; i < board.getXSize(); i++){
            Cell[] verticalCells = board.getVerticalCells(new Coordinate(0, i));
            if (verticalCells[0].getType() != Representation.EMPTY){
                continue;
            }
            for(int j = verticalCells.length-1; j >= 0; j--){
                if (verticalCells[j].getType() == Representation.EMPTY){
                    coordinates.add(new Coordinate(j, i));
                    break;
                }
            }
        }
        return coordinates;
    }
}
