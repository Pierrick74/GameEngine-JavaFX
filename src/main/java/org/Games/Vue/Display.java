package org.Games.Vue;

import org.Games.model.board.Cell;

public class Display {
    private String separationLigne;

    private static Display instance;

    private Display() {
    }

    public static Display getInstance() {
        if (instance == null) {
            instance = new Display();
        }
        return instance;
    }

    public void displayText(String data) {
        System.out.println(data);
    }

    public void display(Cell[][] board) {
        calculateSeparation(board);
        System.out.println(separationLigne);

        for (int i = 0; i < board.length; i++) {
            showLigne(board, i);
            System.out.println(separationLigne);
        }
    }

    private void calculateSeparation(Cell[][] board) {
        int widthScreen = board[0].length;
        separationLigne = "-";
        for  (int x = 0; x < widthScreen; x++) {
            separationLigne += "----";
        }
    }

    private void showLigne(Cell[][] board, int row) {
        System.out.print("|");
        for(int x = 0; x < board[row].length; x++) {
            System.out.print(" " + board[row][x].getType().getValue() + " |");
        }
        System.out.println();
    }
}
