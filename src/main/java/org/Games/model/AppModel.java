package org.Games.model;

import org.Games.model.game.GameType;
import org.Games.observer.Observer;
import org.Games.observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class AppModel implements Subject {
    // Attributs pour la sélection de jeu
    private GameType selectedGameType;
    private boolean shouldLaunchGame;
    private boolean shouldQuit;

    // Liste des observers
    private List<Observer> observers;

    public AppModel() {
        this.selectedGameType = null;
        this.shouldLaunchGame = false;
        this.shouldQuit = false;
        this.observers = new ArrayList<>();
    }

    // Getters
    public GameType getSelectedGameType() {
        return selectedGameType;
    }

    public boolean shouldLaunchGame() {
        return shouldLaunchGame;
    }

    public boolean shouldQuit() {
        return shouldQuit;
    }

    // Setters avec notification
    public void setSelectedGameType(GameType gameType) {
        this.selectedGameType = gameType;
        this.shouldLaunchGame = true;
        notifyObservers();
    }

    public void setShouldQuit(boolean shouldQuit) {
        this.shouldQuit = shouldQuit;
        notifyObservers();
    }

    public void resetLaunchFlag() {
        this.shouldLaunchGame = false;
    }

    // Implémentation de Subject
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.updateState(null);
        }
    }
}

