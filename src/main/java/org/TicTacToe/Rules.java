package org.TicTacToe;

import org.TicTacToe.board.Board;
import org.TicTacToe.board.Cell;
import org.TicTacToe.commun.Coordinate;
import org.TicTacToe.commun.Representation;
import org.TicTacToe.interaction.Display;

public class Rules {

    public boolean isFinished(Board board) {
        return isOver(board);
    }

    private boolean isOver(Board board) {
        boolean over = false;
        int size = board.getSize();

        for(int row = 0; row < size; row++) {
            over = isRowOver(row, board);
            if(over) {
                return true;
            }
        }

        for(int col = 0; col < size; col++) {
            over = isColumnOver(col, board);
            if(over) {
                return true;
            }
        }

        if(isDiagonalOver(board)) {
            return true;
        }

        return isReverseDiagonalOver(board);
    }


    private boolean isRowOver(int row, Board board){
        int size = board.getSize();
        Representation type = null;
        for(int col = 0; col < size; col++) {
            if(board.getCell(new Coordinate(row, col)).getType() ==  Representation.EMPTY) {
                return false;
            }
            if(type == null){
                type = board.getCell(new Coordinate(row, col)).getType();
            }
            if( type != board.getCell(new Coordinate(row, col)).getType()) {
                return false;
            }
        }
        return true;
    }

    private boolean isColumnOver(int col, Board board){
        Representation type = null;
        int size = board.getSize();

        for(int row = 0; row < size; row++) {
            if(board.getCell(new Coordinate(row, col)).getType() ==  Representation.EMPTY) {
                return false;
            }
            if(type == null){
                type = board.getCell(new Coordinate(row, col)).getType();
            }
            if( type != board.getCell(new Coordinate(row, col)).getType()) {
                return false;
            }
        }
        return true;
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

    private boolean isReverseDiagonalOver(Board board){
        Representation type = null;
        int size = board.getSize();

        for(int i = 0; i < size; i++) {
            if(board.getCell(new Coordinate(i, size - 1 - i)).getType() ==  Representation.EMPTY) {
                return false;
            }
            if(type == null){
                type = board.getCell(new Coordinate(i, size - 1 - i)).getType();
            }
            if( type != board.getCell(new Coordinate(i, size - 1 - i)).getType()) {
                return false;
            }
        }
        return true;
    }

    // check position to play with IA
    private boolean isActionToBlock(Cell[] cells, Representation type) {
        int representationNumber = 0;
        for(int i = 0; i < cells.length; i++) {
            if(cells[i].getType() ==  type) {
                representationNumber++;
            }
        }
        return representationNumber == 2 ? true : false;
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
            return new Coordinate(result, result);
        }
        return null;
    }
}