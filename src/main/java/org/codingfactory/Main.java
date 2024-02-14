package org.codingfactory;

import java.util.ArrayList;
import java.util.Scanner;

import java.util.Random;


public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String choice = new String("");
        while (!choice.equals("1")) {
            System.out.println("----Menu----\n 1: Play \n 2: Rules \n 3: Quit");
            choice = scanner.next();

            switch(choice) {

            case "1":
                System.out.println("Play");
                System.out.println("Veuillez entrez le pseudo du premier joueur:");
                String Pseudo1 = scanner.next();
                System.out.println("Veuillez entrez le pseudo du deuxième joueur:");
                String Pseudo2 = scanner.next();
                System.out.println("Bienvenue " + Pseudo1 + " Bienvenue aussi " + Pseudo2);
                break;

            case "2":
                System.out.println(" In this game, players take turns moving their pawn one square either vertically or horizontally and then destroy a square on the board.\n The last player able to make a move wins.\n Constraints include not being able to destroy an occupied square, occupy a destroyed or already occupied square.\n A player unable to move during their turn is declared the loser.\n Winning a game earns a player 5 points, while losing deducts 2 points.");
                break;

            case "3":
                System.out.println("Goodbye bro");
                System.exit(0);
                break;
            default:
                System.out.println("Incorrect choice");
                break;
        }
        }
        int playerNumber = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.println("How many players do you want ?");
                playerNumber = scanner.nextInt();
                if (playerNumber >= 2 && playerNumber <= 4) {
                    validInput = true;
                } else {
                    System.out.println("Please enter a number between 2 and 4.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();
            }
        }

        Game game = new Game(playerNumber);
        ArrayList<Player> Player = new ArrayList<>();
        String direction = new String("");
        int destroyLine = 0;
        int destroyColumn = 0;

        //choose the starter player with a random number
        Random r = new Random();
        int randomPlayer = r.nextInt(playerNumber-1);
        game.currentPlayer = randomPlayer;
        game.displayBoard();
        while (game.loosers.size() < playerNumber) {
            Player p = game.players.get(game.currentPlayer);
            System.out.println("Choose your direction, "+p.pseudo+" ! (UP/DOWN/LEFT/RIGHT)");
            direction = scanner.next();

            switch (direction) {
                case "UP":
                    if(p.y>0 && game.board[p.y-1][p.x] == false) {
                        p.y--;

                    }
                    else{
                        System.out.println("Cannot move up : tile occupied, deleted or out of bounds.");
                    }
                    break;

                case "DOWN":
                    if(p.x<game.dimY-1 && game.board[p.x][p.y + 1] == false) {
                            p.y++;
                    }
                    else{
                        System.out.println("Cannot move down : tile occupied, deleted or out of bounds.");
                    }
                    break;

                case "LEFT":
                    if(p.x>0 && game.board[p.x - 1][p.y] == false) {
                            p.x--;
                        }
                    else{
                        System.out.println("Cannot move left : tile occupied, deleted or out of bounds.");
                    }
                    break;

                case "RIGHT":
                    if(p.x<game.dimX-1 && game.board[p.x + 1][p.y] == false) {
                            p.x++;
                    }
                    else{
                        System.out.println("Cannot move right : tile occupied, deleted or out of bounds.");
                    }
                    break;
            }

            //TODO: Destroy a tile
            System.out.println("Destroy a tile !");
            System.out.println("Input the line.");
            destroyLine = scanner.nextInt();
            System.out.println("Input the column");
            destroyColumn = scanner.nextInt();
            game.board[destroyLine][destroyColumn] = true;
            game.displayBoard();
        }


    }
}