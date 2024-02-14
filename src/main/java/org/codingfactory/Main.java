package org.codingfactory;

public class Main {
    private static Game game = new Game();

    public static void main(String[] args) {
        while (true) {
            switch (game.displayMainMenu()) {
                case 1:
                    game.init();
                    game.update();
                    break;
                case 2:
                    game.displayRules();
                    break;
                case 3:
                    game.displayScoreboard();
                    break;
                case 4:
                    System.exit(0);
                    break;
            }
        }
    }
}