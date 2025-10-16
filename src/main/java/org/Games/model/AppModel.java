package org.Games.model;

import org.Games.Controller.GameController;
import org.Games.model.bd.GameSerialization;
import org.Games.model.bd.Persistence;
import org.Games.model.game.GameModel;
import org.Games.model.game.GameState;
import org.Games.model.game.GameType;
import org.Games.observer.Observer;
import org.Games.observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class AppModel implements Subject {
    private GameType selectedGameType;
    private GameState gameState = null;

    private Persistence dbRepository;

    private List<Observer> observers;

    public AppModel() {
        this.selectedGameType = null;
        this.observers = new ArrayList<>();
        this.dbRepository = new GameSerialization();
    }

    /**
     * Check if a game is saved and notifie the view
     * @param gameType Type of game
     * @return Boolean
     */
    public boolean isGameSaved(GameType gameType) {
        return dbRepository.haveAGameSave(gameType);
    }

    // Getters
    public GameType getSelectedGameType() {
        return selectedGameType;
    }

    public GameModel getSaveGame(GameType gameType) {
        return dbRepository.getGame(gameType);
    }

    public void deleteGame(GameType gameType) {
        dbRepository.deleteGame(gameType);
    }

    // Setters
    public void setSelectedGameType(GameType gameType) {
        this.selectedGameType = gameType;
        notifyObservers();
    }

    // Implémentation of Subject
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
            observer.updateState(gameState);
        }
    }

    @Override
    public void removeAllObserver() {
        observers = new ArrayList<Observer>();
    }
}

