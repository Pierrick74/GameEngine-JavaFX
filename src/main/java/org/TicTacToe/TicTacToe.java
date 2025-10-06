package org.TicTacToe;

public class TicTacToe {
    Integer size;
    Cell[][] board;

    public TicTacToe(Integer size) {
        this.size = size;
        board = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    public void display() {
        Display display = new Display();
        display.display(board);
    }
}
