package org.Games.observer;


public interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void removeAllObserver();
    void notifyObservers();
}
