package org.TicTacToe.board;

import org.TicTacToe.commun.Representation;
import org.TicTacToe.player.Player;
import org.TicTacToe.commun.Coordinate;
import org.TicTacToe.interaction.Display;

/**
 * Responsablility of the board
 */
public class Board {
    Integer xSize;
    Integer ySize;
    Cell[][] board;

    /**
     * build the board with 2 parametes and fill with empty cell
     * @param xSize number of column
     * @param ySize number of rows
     */
    public Board(Integer xSize, Integer ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        board = new Cell[ySize][xSize];
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
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
     * @return true if it full
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

        Cell[] diagonalCells = new Cell[ySize];
        for(int i = 0; i < xSize && i < ySize; i++) {
            diagonalCells[i] = board[i+y][i+x];
        }
        return diagonalCells;
    }

    public Cell[] getReverseDiagonalCells(Coordinate coordinate) {
        int min = Math.min(coordinate.getRow(), xSize - 1 - coordinate.getCol());
        int x = coordinate.getCol() + min;
        int y = coordinate.getRow() + min;

        Cell[] diagonalCells = new Cell[ySize];
        for(int i = 0; (x-i) >= 0 && (y-i) >= 0; i++) {
            diagonalCells[i] = board[y-i][x-i];
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

    public int getSize(){
        return xSize;
    }
}
