package org.Games.observer;


import org.Games.model.game.GameState;

import java.io.Serializable;
import java.util.ArrayList;

public class Observable implements Serializable {
    protected transient ArrayList<Observer> observers = new ArrayList<Observer>();
    protected GameState gameState =  GameState.MAIN;
    protected GameState oldGameState = GameState.MAIN;

    public void addObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }


    public void removeAllObserver() {
        observers = new ArrayList<Observer>();
    }


    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.updateState(gameState);
        }
    }

    //Setter
    public void setGameState(GameState gameState) {
        oldGameState = this.gameState;
        this.gameState = gameState;
        notifyObservers();
    }
}
