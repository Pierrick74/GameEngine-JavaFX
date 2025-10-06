package org.TicTacToe;

public class Display {
    private String separationLigne;


    public void display(Cell[][] board) {
        calculateSeparation(board);
        System.out.println(separationLigne);

        for (int i = 0; i < board.length; i++) {
            showLigne(board, i);
            System.out.println(separationLigne);
        }
    }

    private void calculateSeparation(Cell[][] board) {
        Integer widthScreen = board.length;
        separationLigne = "-";
        for  (int x = 0; x < widthScreen; x++) {
            separationLigne += "----";
        }
    }

    private void showLigne(Cell[][] board, int row) {
        System.out.print("|");
        for(int x = 0; x < board[row].length; x++) {
            System.out.print(board[row][x] + "|");
        }
        System.out.println();
    }
}
