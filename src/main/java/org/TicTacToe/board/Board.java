package org.TicTacToe.board;

import org.TicTacToe.Player;
import org.TicTacToe.commun.Coordinate;
import org.TicTacToe.interaction.Display;

public class Board {
    Integer size;
    Cell[][] board;

    public Board(Integer size) {
        this.size = size;
        board = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    public void display() {
        Display.getInstance().display(board);
    }

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

    public Cell getCell(Coordinate coordinate) {
        return board[coordinate.getRow()][coordinate.getCol()];
    }

    public void setCell(Coordinate coordinate, Player player) {
        board[coordinate.getRow()][coordinate.getCol()] = new Cell(player);
    }

    public int getSize() {
        return size;
    }
}
