package org.TicTacToe.commun;

public class Coordinate {
    private final int col;
    private final int row;

    public Coordinate(int row, int col) {
        this.col = col;
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    @Override
    public String toString() {
        return "la colonne est " + col + ", la row est " + row;
    }
}
