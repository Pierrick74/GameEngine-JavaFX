package org.TicTacToe.commun;

public enum Representation {
    CROSS("X"),
    ROUND("O"),
    EMPTY(" ");

    private String abreviation ;

    private Representation(String abreviation) {
        this.abreviation = abreviation ;
    }

    public String getValue() {
        return  this.abreviation ;
    }
}
