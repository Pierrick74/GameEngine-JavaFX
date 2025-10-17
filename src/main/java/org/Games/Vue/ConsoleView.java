package org.Games.Vue;

import org.Games.Controller.GameController;
import org.Games.model.game.GameState;
import org.Games.observer.Observer;

public class ConsoleView implements Observer {
    private GameController gameController;

    private static ConsoleView instance;

    private ConsoleView() {
        Display.getInstance().displayText("Application de jeux démarrée");
        showAllGameAvailable();
    }

    public static ConsoleView getInstance() {
        if (instance == null) {
            instance = new ConsoleView();
        }
        return instance;
    }

    @Override
    public void updateState(GameState gameState) {
        switch (gameState) {
            case ASKFORCHOSEGAME:
                showAllGameAvailable();
                break;

            case ASKTORESTOREGAME:
                showRestoreGameQuestion();
                break;

            case DISPLAYBOARD:
                if (gameController != null) {
                    showClickedCell();
                    showBoard();
                }
                break;

            case DISPLAYONLYBOARD:
                if (gameController != null) {
                    showPlayers();
                    showBoard();
                }
                break;

            case ASKFORCHOSENUMBEROFPLAYER:
                showAskForNumberOfPlayer();
                break;

            case FINISHED:
                if (gameController != null) {
                    Display.getInstance().displayText("Partie terminée");
                    showBoard();
                    String winner = gameController.getWinner();
                    if (winner != null) {
                        Display.getInstance().displayText(winner);
                    }
                }
                break;

            case INVALIDINPUT:
                Display.getInstance().displayText("Entrée invalide. Veuillez réessayer.");
                break;

        }
    }

    private void showAllGameAvailable(){
        Display.getInstance().displayText("Choisissez un jeu?");
        Display.getInstance().displayText("0: TicTacToe");
        Display.getInstance().displayText("1: Gomoku");
        Display.getInstance().displayText("2: Power4");

    }

    private void showRestoreGameQuestion(){
        Display.getInstance().displayText("Voulez vous charger la sauvegarde ?");
        Display.getInstance().displayText("0: Oui");
        Display.getInstance().displayText("1: Non");
    }

    private void showAskForNumberOfPlayer(){
        Display.getInstance().displayText("Comment voulez-vous jouer?");
        Display.getInstance().displayText("2: 2 Joueurs Humain");
        Display.getInstance().displayText("1: 1 humain et un joueur artificiel");
        Display.getInstance().displayText("0: 2 Joueurs artificiels");
    }

    private void showPlayers(){
        Display.getInstance().displayText(gameController.getPlayerName());
    }

    private void showClickedCell() {
        showPlayers();
        Display.getInstance().displayText("Clicked on cell " + gameController.getLastRow() + " " + gameController.getLastColumn());
    }

    private void showBoard() {
        Display.getInstance().display(gameController.getBoard());
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void clearGameController() {
        this.gameController = null;
    }
}
