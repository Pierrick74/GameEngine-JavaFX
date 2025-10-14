package org.Games.model;

import javafx.stage.Stage;
import org.Games.model.bd.GameSerialization;
import org.Games.model.bd.Persistence;
import org.Games.model.game.GameState;
import org.Games.model.game.GameType;
import org.Games.observer.Observer;
import org.Games.observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class AppModel implements Subject {
    // Attributs pour la sélection de jeu
    private GameType selectedGameType;
    private boolean isGameSaved;
    private boolean shouldQuit;
    private Persistence dbRepository;
    private GameState gameState = null;

    // Liste des observers
    private List<Observer> observers;

    public AppModel() {
        this.selectedGameType = null;
        this.shouldQuit = false;
        this.observers = new ArrayList<>();
        this.dbRepository = new GameSerialization();
    }

    // Getters
    public GameType getSelectedGameType() {
        return selectedGameType;
    }

    public boolean shouldQuit() {
        return shouldQuit;
    }

    // Setters avec notification
    public void setSelectedGameType(GameType gameType) {
        this.selectedGameType = gameType;
        notifyObservers();
    }

    public void setShouldQuit(boolean shouldQuit) {
        this.shouldQuit = shouldQuit;
        notifyObservers();
    }

    public boolean isGameSaved(GameType gameType) {
        isGameSaved = dbRepository.haveAGameSave(gameType);
        if (isGameSaved){
            gameState = GameState.CHECKSAVE;
            notifyObservers();
        }
        return isGameSaved;
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
            observer.updateState(gameState);
        }
    }

    @Override
    public void removeAllObserver() {
        observers = new ArrayList<Observer>();
    }
}

