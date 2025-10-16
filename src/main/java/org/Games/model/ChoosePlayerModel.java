package org.Games.model;

import org.Games.model.game.GameState;
import org.Games.observer.Observable;

public class ChoosePlayerModel extends Observable {
    //Setter
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
        notifyObservers();
    }

}
