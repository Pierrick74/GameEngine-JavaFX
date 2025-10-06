package org.TicTacToe.player;

import org.TicTacToe.commun.Representation;

import java.util.Random;

public class ArtificialPlayer extends Player {

    public ArtificialPlayer(Representation type) {
        super(type);
    }

    public int getRandomNumber(int max) {
        Random rand = new Random();
        return rand.nextInt(max);
    }
}
