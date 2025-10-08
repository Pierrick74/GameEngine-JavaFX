package org.TicTacToe;

import org.TicTacToe.board.Board;
import org.TicTacToe.board.Cell;
import org.TicTacToe.commun.Coordinate;
import org.TicTacToe.commun.FinishName;
import org.TicTacToe.commun.Representation;


public class Rules {
    int consecutiveNumber;

    public Rules(int consecutiveNumber) {
        this.consecutiveNumber = consecutiveNumber;
    }

    public boolean isFinished(Board board, Coordinate lastRoundCoordinate) {
        Cell[] horizontalCells = board.getHorizontalCells(lastRoundCoordinate);
        if (isOverCells(horizontalCells)) return true;

        Cell[] verticalCells = board.getVerticalCells(lastRoundCoordinate);
        if (isOverCells(verticalCells)) return true;

        Cell[] diagonalCells = board.getDiagonalCells(lastRoundCoordinate);
        if (isOverCells(diagonalCells)) return true;

        Cell[] reverseDiagonalCells = board.getReverseDiagonalCells(lastRoundCoordinate);
        return isOverCells(reverseDiagonalCells);
    }

    public boolean isOverCells(Cell[] cells){
        Representation currentRepresentation = cells[0].getType();
        int number = 1;
        for(int i = 1; i < cells.length; i++){
            if (cells[i].getType().equals(currentRepresentation) && cells[i].getType() != Representation.EMPTY){
                number++;
                if(number == consecutiveNumber) return true;
            } else {
                number = 1;
                currentRepresentation = cells[i].getType();
            }
        }
        return false;
    }

    public FinishName getResult(Board board, Coordinate lastCoordinate) {
        if(isFinished(board, lastCoordinate)){
           return FinishName.WIN;
        }

        if(board.isFull()){
            return FinishName.TIE;
        }
        return null;
    }

}