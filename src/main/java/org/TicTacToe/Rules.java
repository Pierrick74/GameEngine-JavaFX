package org.TicTacToe;

import org.TicTacToe.board.Board;
import org.TicTacToe.board.Cell;
import org.TicTacToe.commun.Coordinate;
import org.TicTacToe.commun.Representation;

public class Rules {

    public boolean isFinished(Board board) {
        return isOver(board);
    }

    private boolean isOver(Board board) {
        boolean over = false;
        int size = board.getSize();

        for(int row = 0; row < size; row++) {
            over = isRowOver(board);
            if(over) {
                return true;
            }
        }

        for(int col = 0; col < size; col++) {
            over = isColumnOver(board);
            if(over) {
                return true;
            }
        }

        if(isDiagonalOver(board)) {
            return true;
        }

        return isReverseDiagonalOver(board);
    }


    private boolean isRowOver(Board board){
        int size = board.getSize();

        for(int irow = 0; irow < size; irow++) {
            Cell[] cells = board.getCellsInRow(irow);
            if(isCellsOver(cells)) {
                return true;
            }
        }
        return false;
    }

    private boolean isCellsOver(Cell[] cells) {
        int representationCross = 0;
        int representationRound = 0;
        for(int i = 0; i < cells.length; i++) {
            Cell cell = cells[i];
            if(cell.getType() == Representation.CROSS) {
                representationCross++;
            }
            if(cell.getType() == Representation.ROUND) {
                representationRound++;
            }
        }
        return representationCross == 3 || representationRound == 3;
    }

    private boolean isColumnOver(Board board){
        int size = board.getSize();

        for(int icol = 0; icol < size; icol++) {
            Cell[] cells = board.getCellsInRow(icol);
            if(isCellsOver(cells)) {
                return true;
            }
        }
        return false;
    }

    private boolean isDiagonalOver(Board board){
        Representation type = null;
        int size = board.getSize();

        for(int i = 0; i < size; i++) {
            if(board.getCell(new Coordinate(i, i)).getType() ==  Representation.EMPTY) {
                return false;
            }
            if(type == null){
                type = board.getCell(new Coordinate(i, i)).getType();
            }
            if( type != board.getCell(new Coordinate(i, i)).getType()) {
                return false;
            }
        }
        return true;
    }

    private boolean isReverseDiagonalOver(Board board) {
        Representation type = null;
        int size = board.getSize();

        for (int i = 0; i < size; i++) {
            if (board.getCell(new Coordinate(i, size - 1 - i)).getType() == Representation.EMPTY) {
                return false;
            }
            if (type == null) {
                type = board.getCell(new Coordinate(i, size - 1 - i)).getType();
            }
            if (type != board.getCell(new Coordinate(i, size - 1 - i)).getType()) {
                return false;
            }
        }
        return true;
    }
}