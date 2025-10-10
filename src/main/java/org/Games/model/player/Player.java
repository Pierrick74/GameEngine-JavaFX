package org.Games.model.player;

import java.io.Serializable;

public class Player implements Serializable {
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
