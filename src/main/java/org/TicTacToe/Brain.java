package org.TicTacToe;

import org.TicTacToe.board.Board;
import org.TicTacToe.board.Cell;
import org.TicTacToe.commun.Coordinate;
import org.TicTacToe.commun.Representation;

public class Brain {
    // check position to play with IA
    private boolean isActionToBlock(Cell[] cells, Representation type) {
        int representationNumber = 0;
        int EmptyPlace = 0;
        for(int i = 0; i < cells.length; i++) {
            if(cells[i].getType() ==  type) {
                representationNumber++;
            }
            if(cells[i].getType() ==  Representation.EMPTY) {
                EmptyPlace++;
            }
        }
        return representationNumber == 2 && EmptyPlace == 1;
    }

    private int getPositionToBlock(Cell[] cells) {
        for(int i = 0; i < cells.length; i++) {
            if(cells[i].getType() ==  Representation.EMPTY) {
                return i;
            }
        }
        return 0;
    }

    public Coordinate getCoordinateToBlock(Board board, Representation type) {
        int size = board.getSize();
        Representation typeToBlock = type == Representation.CROSS ? Representation.ROUND : Representation.CROSS;
        Cell[] cells;

        for(int row = 0; row < size; row++) {
            cells = board.getCellsInRow(row);
            if(isActionToBlock(cells, typeToBlock)) {
                int column = getPositionToBlock(cells);
                return new Coordinate(row, column);
            }
        }

        for(int col = 0; col < size; col++) {
            cells = board.getCellsInColumn(col);
            if(isActionToBlock(cells, typeToBlock)) {
                int row = getPositionToBlock(cells);
                return new Coordinate(row, col);
            }
        }

        cells = board.getCellsInDiagonal();
        if(isActionToBlock(cells, typeToBlock)) {
            int pos = getPositionToBlock(cells);
            return new Coordinate(pos, pos);
        }

        cells = board.getCellsInReverseDiagonal();
        if(isActionToBlock(cells, typeToBlock)) {
            int pos = getPositionToBlock(cells);
            int result = size - 1 - pos;
            return new Coordinate(pos, result);
        }
        return null;
    }
}
