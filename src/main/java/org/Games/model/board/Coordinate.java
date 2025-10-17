package org.Games.model.board;

import java.io.Serializable;

public class Coordinate implements Serializable {
    private final int col;
    private final int row;

    public Coordinate(int row, int col) {
        if(row < 0 || col < 0 ){
            throw new IllegalArgumentException("Invalid coordinate");
        }
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
