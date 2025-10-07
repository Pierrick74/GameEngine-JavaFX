package org.TicTacToe;

import org.TicTacToe.board.Board;
import org.TicTacToe.board.Cell;
import org.TicTacToe.commun.Coordinate;
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
        if (isOverCells(reverseDiagonalCells)) return true;

        return false;
    }

    public boolean isOverCells(Cell[] cells){
        Representation currentRepresentation = cells[0].getType();
        int consecutiveNumber = 1;
        for(int i = 1; i < cells.length; i++){
            if (cells[i].getType().equals(currentRepresentation)){
                consecutiveNumber++;
            } else {
                consecutiveNumber = 1;
                currentRepresentation = cells[i].getType();
            }
        }
        return consecutiveNumber == 3;
    }

}