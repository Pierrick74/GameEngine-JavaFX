package org.Games.model.board;

import org.Games.model.player.Representation;
import org.Games.model.player.Player;

import java.io.Serializable;

/**
 * Responsibility of the board
 */
public class Board implements Serializable {
    private Integer xSize;
    private Integer ySize;
    private Cell[][] board;

    /**
     * build the board with 2 parameters and fill with empty cell
     * @param xSize number of column
     * @param ySize number of rows
     */
    public Board(Integer xSize, Integer ySize) {
        if (xSize < 0 || ySize < 0 || xSize == null || ySize == null) {
            throw new IllegalArgumentException("Invalid board parameters");
        }

        this.xSize = xSize;
        this.ySize = ySize;
        board = new Cell[ySize][xSize];
        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                board[i][j] = new Cell();
            }
        }
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

    /**
     * use for get all cell at same row
     * @param coordinate
     * @return array of Cell
     */
    public Cell[] getHorizontalCells(Coordinate coordinate) {
        if (coordinate == null || coordinate.getRow() < 0 || coordinate.getCol() < 0) {
            throw new IllegalArgumentException("Invalid coordinate");
        }
        if(xSize < 0 || xSize == null) {
            throw new IllegalArgumentException("Invalid size");
        }
        if(board == null || board.length == 0) {
            throw new IllegalArgumentException("Invalid board parameters");
        }

        Cell[] horizontalCells = new Cell[xSize];

        for(int col = 0; col < xSize; col++) {
            horizontalCells[col] = board[coordinate.getRow()][col];
        }
        return horizontalCells;
    }

    /**
     * use for gat all cell at same column of coordinate
     * @param coordinate
     * @return array of Cell
     */
    public Cell[] getVerticalCells(Coordinate coordinate) {
        if (coordinate == null || coordinate.getRow() < 0 || coordinate.getCol() < 0) {
            throw new IllegalArgumentException("Invalid coordinate");
        }
        if(ySize < 0 || ySize == null) {
            throw new IllegalArgumentException("Invalid size");
        }
        if(board == null || board.length == 0) {
            throw new IllegalArgumentException("Invalid board parameters");
        }

        Cell[] verticalCells = new Cell[ySize];
        for(int row = 0; row < ySize; row++) {
            verticalCells[row] = board[row][coordinate.getCol()];
        }
        return verticalCells;
    }

    /**
     * use to get all the Cell in the diagonal of coordinate
     * @param coordinate
     * @return array of Cell
     */
    public Cell[] getDiagonalCells(Coordinate coordinate) {
        if (coordinate == null || coordinate.getRow() < 0 || coordinate.getCol() < 0) {
            throw new IllegalArgumentException("Invalid coordinate");
        }
        if(ySize < 0 || ySize == null || xSize == null || xSize < 0) {
            throw new IllegalArgumentException("Invalid size");
        }
        if(board == null || board.length == 0) {
            throw new IllegalArgumentException("Invalid board parameters");
        }

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
        if (coordinate == null || coordinate.getRow() < 0 || coordinate.getCol() < 0) {
            throw new IllegalArgumentException("Invalid coordinate");
        }
        if(ySize < 0 || ySize == null || xSize == null || xSize < 0) {
            throw new IllegalArgumentException("Invalid size");
        }
        if(board == null || board.length == 0) {
            throw new IllegalArgumentException("Invalid board parameters");
        }

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
        if (coordinate == null || coordinate.getRow() < 0 || coordinate.getCol() < 0) {
            throw new IllegalArgumentException("Invalid coordinate");
        }
        return getCell(coordinate).getType() == Representation.EMPTY;
    }

    /**
     * use for get a specifical Cell
     * @param coordinate position (row coll) of the cell
     * @return a Cell
     */
    public Cell getCell(Coordinate coordinate) {
        if (coordinate == null || coordinate.getRow() < 0 || coordinate.getCol() < 0) {
            throw new IllegalArgumentException("Invalid coordinate");
        }
        if(board == null || board.length == 0) {
            throw new IllegalArgumentException("Invalid board parameters");
        }
        return board[coordinate.getRow()][coordinate.getCol()];
    }

    /**
     * use for put a player in a cell
     * @param coordinate position (row coll) of the cell
     * @param player player to put
     */
    public void setCell(Coordinate coordinate, Player player) {
        if(board == null || board.length == 0) {
            throw new IllegalArgumentException("Invalid board parameters");
        }
        if (coordinate == null || coordinate.getRow() < 0 || coordinate.getCol() < 0) {
            throw new IllegalArgumentException("Invalid coordinate");
        }
        if (player == null) {
            throw new IllegalArgumentException("Invalid player");
        }

        board[coordinate.getRow()][coordinate.getCol()] = new Cell(player);
    }

    public int getXSize(){
        return xSize;
    }

    public int getYSize(){
        return ySize;
    }

    public Board getClone() {
        if(ySize < 0 || ySize == null || xSize == null || xSize < 0) {
            throw new IllegalArgumentException("Invalid size");
        }
        if(board == null || board.length == 0) {
            throw new IllegalArgumentException("Invalid board parameters");
        }

        Board clone = new Board(xSize, ySize);
        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                if (board[i][j].player == null) {
                    clone.board[i][j] = new Cell();
                } else {
                    clone.board[i][j] = new Cell(board[i][j].player);
                }
            }
        }
        return clone;
    }

    public Cell[][] getBoard() {
        return board;
    }
}
