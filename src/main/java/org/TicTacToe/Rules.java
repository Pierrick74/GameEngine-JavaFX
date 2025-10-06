package org.TicTacToe;

import org.TicTacToe.board.Board;
import org.TicTacToe.commun.Coordinate;
import org.TicTacToe.commun.Representation;
import org.TicTacToe.interaction.Display;

public class Rules {

    public boolean isFinished(Board board) {

        if(isOver(board)) {
            return true;
        }

        return false;
    }

    private boolean isOver(Board board) {
        Boolean over = false;
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

        if(isReverseDiagonalOver(board)) {
            return true;
        }

        return false;
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

        for(int i = size-1; i >= 0; i--) {
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
}
