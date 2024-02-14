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
        Random r = new Random();
        String direction = new String("");
        String moveValue;
        int randomPlayer = r.nextInt(playerNumber-1);
        Player p = game.players.get(randomPlayer);
        game.currentPlayer = p.getPseudo();
        while (game.loosers.size()<playerNumber-1){
            System.out.println("choose the direction to move. H: horizontal V: vertical");
            while ((direction !="H") && (direction !="V")) {
                System.out.println("please enter a valid direction");
                direction = scanner.next();
            }
            System.out.println("choose a value . -1 : left or down 1 : up or right");
            while ((!moveValue.equals("-1")) && (!moveValue.equals("1")){
                System.out.println("please enter a valid value");
                moveValue = scanner.next();
            }


        }
        game.displayBoard();
        scanner.close();

    }
}