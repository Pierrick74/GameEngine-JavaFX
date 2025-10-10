package org.Games.model.board;

import org.Games.model.player.Representation;
import org.Games.model.player.Player;
import org.Games.Vue.Display;

import java.io.Serializable;

/**
 * Responsibility of the board
 */
public class Board implements Serializable {
    Integer xSize;
    Integer ySize;
    Cell[][] board;

    /**
     * build the board with 2 parameters and fill with empty cell
     * @param xSize number of column
     * @param ySize number of rows
     */
    public Board(Integer xSize, Integer ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        board = new Cell[ySize][xSize];
        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    /*
    use for display
     */
    public void display() {
        Display.getInstance().display(board);
    }

    /**
     * check if board is full
     * @return true if it fulls
     */
    public Boolean isFull() {
        for(int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                if (board[i][j].player == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public Cell[] getHorizontalCells(Coordinate coordinate) {
        Cell[] horizontalCells = new Cell[xSize];

        for(int col = 0; col < xSize; col++) {
            horizontalCells[col] = board[coordinate.getRow()][col];
        }
        return horizontalCells;
    }

    public Cell[] getVerticalCells(Coordinate coordinate) {
        Cell[] verticalCells = new Cell[ySize];
        for(int row = 0; row < ySize; row++) {
            verticalCells[row] = board[row][coordinate.getCol()];
        }
        return verticalCells;
    }

    public Cell[] getDiagonalCells(Coordinate coordinate) {
        int min = Math.min(coordinate.getRow(), coordinate.getCol());
        int x = coordinate.getCol() - min;
        int y = coordinate.getRow() - min;

        int diagonalLength = Math.min(xSize - x, ySize - y);
        Cell[] diagonalCells = new Cell[diagonalLength];
        for(int i = 0; i < diagonalLength; i++) {
            diagonalCells[i] = board[i+y][i+x];
        }
        return diagonalCells;
    }

    public Cell[] getReverseDiagonalCells(Coordinate coordinate) {
        int min = Math.min(coordinate.getRow(), xSize - 1 - coordinate.getCol());
        int x = coordinate.getCol() + min;
        int y = coordinate.getRow() - min;

        int diagonalLength = Math.min(x + 1, ySize - y);
        Cell[] diagonalCells = new Cell[diagonalLength];
        for(int i = 0; i < diagonalLength; i++) {
            diagonalCells[i] = board[y+i][x-i];
        }
        return diagonalCells;
    }

    /**
     * check if call have a player inside
     * @param coordinate position (row coll) of the cell
     * @return true if there is a player inside
     */
    public Boolean isEmptyCase(Coordinate coordinate) {
        return getCell(coordinate).getType() == Representation.EMPTY;
    }

    /**
     * use for get a specifical Cell
     * @param coordinate position (row coll) of the cell
     * @return a Cell
     */
    public Cell getCell(Coordinate coordinate) {
        return board[coordinate.getRow()][coordinate.getCol()];
    }

    /**
     * use for put a player in a cell
     * @param coordinate position (row coll) of the cell
     * @param player player to put
     */
    public void setCell(Coordinate coordinate, Player player) {
        board[coordinate.getRow()][coordinate.getCol()] = new Cell(player);
    }

    public int getXSize(){
        return xSize;
    }

    public int getYSize(){
        return ySize;
    }

    public Board getClone() {
        Board clone = new Board(xSize, ySize);
        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                clone.board[i][j] = new Cell(board[i][j].player);
            }
        }
        return clone;
    }
}
