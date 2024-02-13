package org.codingfactory;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String choice = new String("");
        while (choice != "1") {
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
                System.out.println("Choix incorrect");
                break;
        }
        }
    }
}