package org.codingfactory;

import java.util.*;
public class Game {
    ArrayList<Player> players;
    int board[][];

    public Game(int nPlayers) {
        this.players = new ArrayList<>();
        String pseudo[] = new String[4];
        pseudo[0] = "Firmin";
        pseudo[1] = "Eudes";
        pseudo[2] = "Ulysse";
        pseudo[3] = "Roman";

        for (int i = 0; i< nPlayers; i++){
            this.players.add(new Player(pseudo[i], 3+i, 5));
        }

        this.board = new int[10][11];
    }
    public void displayBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                boolean playerFound = false;
                for (Player player : players) {
                    if (player.getX() == i && player.getY() == j) {
                        String playerName = player.getPseudo();
                        //if (playerName.length() >= 2) {
                            //System.out.print(playerName.substring(0, 2) + " ");
                        //} //else {
                            System.out.print(playerName.charAt(0) + " ");
                        //}
                        playerFound = true;
                        break;
                    }
                }
                if (!playerFound) {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }

}
