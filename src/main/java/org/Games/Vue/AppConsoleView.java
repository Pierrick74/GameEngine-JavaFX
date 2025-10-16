package org.Games.Vue;

import org.Games.model.game.GameState;
import org.Games.observer.Observer;

public class AppConsoleView implements Observer {

    public AppConsoleView() {
        Display.getInstance().displayText("Application de jeux démarrée");
        showAllGameAvailable();
    }

    @Override
    public void updateState(GameState gameState) {
        switch (gameState) {
            case ASKTORESTOREGAME:
                showRestoreGameQuestion();
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
}
