package org.codingfactory;

import java.util.*;
public class Game {
    ArrayList<Player> players;
    int board[][];

    public Game(int nPlayers) {
        String pseudo[] = new String[4];
        pseudo[0] = "Alpha";
        pseudo[1] = "Bravo";
        pseudo[2] = "Charlie";
        pseudo[3] = "Delta";

        for (int i = 0; i<= nPlayers; i++){
            this.players.add(new Player(pseudo[i], 5+i, 6));
        }

        this.board = new int[10][11];
    }
}
