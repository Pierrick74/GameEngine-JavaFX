package org.TicTacToe;

import org.TicTacToe.commun.GameType;
import org.TicTacToe.commun.Representation;
import org.TicTacToe.interaction.Display;
import org.TicTacToe.interaction.Terminal;
import org.TicTacToe.player.ArtificialPlayer;
import org.TicTacToe.player.Player;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Game game;

    public static void main(String[] args) throws InterruptedException {
        selectGame();
        game.start();
    }

    private static void selectGame() {
        Display.getInstance().displayText("Quel jeu voulez-vous jouer?");
        Display.getInstance().displayText("0: TICTACTOE");
        Display.getInstance().displayText("1: GOMOKU");
        Display.getInstance().displayText("2: POWER4");
        int result = Terminal.getInstance().askForInteger(3);

        switch (result) {
            case 0:
                game = new Game(GameType.TICTACTOE);
                break;
            case 1:
                game = new Game(GameType.GOMOKU);
                break;
            case 2:
                game = new Game(GameType.POWER4);
                break;
        }
    }
}