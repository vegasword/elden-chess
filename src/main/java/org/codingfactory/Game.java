package org.codingfactory;

import java.util.*;
public class Game {
    ArrayList<Player> players;
    boolean board[][];
    int dimX = 10;
    int dimY = 11;

    int currentPlayer;
    ArrayList<Player> loosers;
    public Game(int nPlayers) {
        this.players = new ArrayList<>();
        this.loosers = new ArrayList<>();
        String pseudo[] = new String[4];
        pseudo[0] = "Firmin";
        pseudo[1] = "Eudes";
        pseudo[2] = "Ulysse";
        pseudo[3] = "Roman";

        for (int i = 0; i< nPlayers; i++){
            this.players.add(new Player(pseudo[i], 3+i, 5));
        }

        this.board = new boolean [11][10];
    }
    public void displayBoard() {
        for (int j = 0; j < dimY; j++){
            for (int i = 0; i < dimX; i++) {
                boolean playerFound = false;
                for (Player player : players) {
                    if (player.x == i && player.y == j) {
                        String playerName = player.pseudo;
                        System.out.print(playerName.charAt(0) + " ");
                        playerFound = true;
                        break;
                    }
                }
                if (!playerFound){
                    if (board[j][i] == false) {
                    System.out.print("- ");
                    }
                    else{
                    System.out.print("X ");
                }

                }

            }
            System.out.println();
        }
    }

}
