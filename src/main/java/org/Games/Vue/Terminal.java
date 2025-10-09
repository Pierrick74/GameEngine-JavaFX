package org.Games.Vue;

import java.util.Scanner;

public class Terminal {

    private static Terminal instance;
    final private Scanner scanner;

    private Terminal() {
        this.scanner = new Scanner(System.in);
    }

    public static Terminal getInstance() {
        if (instance == null) {
            instance = new Terminal();
        }
        return instance;
    }

    public Integer askForInteger(int maxValue) {
        boolean isValide = false;
        int number = 0;
        while(!isValide) {
            if (scanner.hasNextInt()) {
                number = scanner.nextInt();
                System.out.println("Nombre saisi: " + number);

                if( number < maxValue ) {
                    isValide = true;
                } else {
                    System.out.println("Merci de rentrer un nombre valide entre 0 et " +  (maxValue-1));
                }
            } else {
                System.out.println("Erreur: ce n'est pas un entier valide, recommencer");
                scanner.next();
            }
        }
        return number;
    }
}
