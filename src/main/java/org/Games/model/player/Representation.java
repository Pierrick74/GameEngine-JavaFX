package org.Games.model.player;

public enum Representation {
    CROSS("X", "/org/Games/JavaFX/assets/icone/cross.png"),
    ROUND("O", "/org/Games/JavaFX/assets/icone/round.png"),
    EMPTY(" ", "/org/Games/JavaFX/assets/icone/empty.png"),
    BLACK("B", "/org/Games/JavaFX/assets/icone/black.png"),
    WHITE("W", "/org/Games/JavaFX/assets/icone/white.png"),
    YELLOW("Y", "/org/Games/JavaFX/assets/icone/yellow.png"),
    RED("R", "/org/Games/JavaFX/assets/icone/red.png");

    private final String abreviation;
    private final String iconPath;

    Representation(String abreviation, String iconPath) {
        this.abreviation = abreviation;
        this.iconPath = iconPath;
    }

    public String getValue() {
        return this.abreviation;
    }

    public String getIcone() {
        return this.iconPath;
    }
}
