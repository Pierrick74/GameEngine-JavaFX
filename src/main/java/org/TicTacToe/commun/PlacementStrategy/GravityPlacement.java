package org.TicTacToe.commun.PlacementStrategy;

import org.TicTacToe.board.Board;
import org.TicTacToe.board.Cell;
import org.TicTacToe.commun.Coordinate;
import org.TicTacToe.commun.Exception.OutOfBoardException;
import org.TicTacToe.commun.Representation;
import org.TicTacToe.interaction.Display;
import org.TicTacToe.interaction.Terminal;

import java.util.ArrayList;
import java.util.List;

public class GravityPlacement implements PlacementStrategie{
    public Coordinate askForPlacement(Board board) {
        int xSize = board.getXSize();

        Coordinate coordinate = null;
        boolean isValide = false;

        while (!isValide) {
            Display.getInstance().displayText("quel est la colonne que vous voulez");
            int col = Terminal.getInstance().askForInteger(xSize);

            try {
                int row = getRowAvailable(board, col);
                coordinate = new Coordinate(row, col);
                isValide = true;
                Display.getInstance().displayText(coordinate.toString());
            } catch (OutOfBoardException e){
                Display.getInstance().displayText(e.getMessage());

            }
        }
        return coordinate;
    }

    private int getRowAvailable(Board board, int col) throws OutOfBoardException {
        Cell[] verticalCells = board.getVerticalCells(new Coordinate(0, col));
        if (verticalCells[0].getType() != Representation.EMPTY){
            throw new OutOfBoardException("Colonne Saturée");
        }
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
