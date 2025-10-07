package org.TicTacToe.board;

import org.TicTacToe.commun.Representation;
import org.TicTacToe.player.Player;
import org.TicTacToe.commun.Coordinate;
import org.TicTacToe.interaction.Display;

/**
 * Responsablility of the board
 */
public class Board {
    Integer size;
    Cell[][] board;

    /**
     * Init Board
     * @param size : size of the square
     */
    public Board(Integer size) {
        this.size = size;
        board = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
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
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j].player == null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * used to recover all call in a specifical Row
     * @param row number of the row
     * @return a array of Cell
     */
    public Cell[] getCellsInRow(int row) {
        Cell[] cells = new Cell[size];

        for(int col = 0; col < size; col++) {
            cells[col] = board[row][col];
        }
        return cells;
    }

    /**
     * used to recover all call in a specifical Col
     * @param col number of the column
     * @return an array of Cell
     */
    public Cell[] getCellsInColumn(int col) {
        Cell[] cells = new Cell[size];

        for(int row = 0; row < size; row++) {
            cells[row] = board[row][col];
        }
        return cells;
    }

    /**
     * used to recover all call in a normal diagonal
     * @return a array of Cell
     */
    public Cell[] getCellsInDiagonal() {
        Cell[] cells = new Cell[size];

        for(int i = 0; i < size; i++) {
            cells[i] = board[i][i];
        }
        return cells;
    }

    /**
     * used to recover all call in a reverse diagonal
     * @return an array of Cell
     */
    public Cell[] getCellsInReverseDiagonal() {
        Cell[] cells = new Cell[size];

        for(int i = 0; i < size; i++) {
            cells[i] = board[i][size - 1 - i];
        }
        return cells;
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

    /**
     * use for get size of the board
     * @return size in int
     */
    public int getSize() {
        return size;
    }
}
