package org.codingfactory;

import java.util.Scanner;


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
                //TODO: create a new game and display it
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
        game.displayBoard();
        scanner.close();
    }
}