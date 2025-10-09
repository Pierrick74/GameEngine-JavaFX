package org.TicTacToe.commun;

public enum Representation {
    CROSS("X"),
    ROUND("O"),
    EMPTY(" "),
    BLACK("B"),
    WHITE("W"),
    YELLOW("Y"),
    RED("R");

    private final String abreviation ;

    Representation(String abreviation) {
        this.abreviation = abreviation ;
    }

    public String getValue() {
        return  this.abreviation ;
    }
}
