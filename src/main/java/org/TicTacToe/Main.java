package org.TicTacToe;

import org.TicTacToe.commun.GameType;
import org.TicTacToe.interaction.Display;
import org.TicTacToe.interaction.Terminal;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) throws InterruptedException {
        GameType gameType = selectGame();
        Controlleur controlleur = new Controlleur(gameType);
        controlleur.start();
    }

    private static GameType selectGame() {
        Display.getInstance().displayText("Quel jeu voulez-vous jouer?");
        Display.getInstance().displayText("0: TICTACTOE");
        Display.getInstance().displayText("1: GOMOKU");
        Display.getInstance().displayText("2: POWER4");
        int result = Terminal.getInstance().askForInteger(3);

        switch (result) {
            case 0:
                return GameType.TICTACTOE;
            case 1:
                return GameType.GOMOKU;
            default:
                return GameType.POWER4;
        }
    }
}