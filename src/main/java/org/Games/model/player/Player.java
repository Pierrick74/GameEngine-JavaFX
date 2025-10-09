package org.Games.model.player;

public class Player {
    Representation type;

    public Player(Representation type) {
        this.type = type;
    }

    public Representation getType() {
        return type;
    }

    @Override
    public String toString() {
        return type.toString();
    }
}
