package org.TicTacToe.commun.Exception;

public class OutOfBoardException extends Throwable {
    public OutOfBoardException(String message) {
        super(message);
    }
}
