package org.codingfactory;

import java.util.*;

/**
 * This class is a custom wrapper of the built-in Java Scanner object.
 */
public class SafeInput {

    private Scanner scanner;

    public SafeInput() {
        scanner = new Scanner(System.in);
    }

    public void pause() {
        System.out.print("Press any key to continue...");
        try { System.in.read(); scanner.nextLine(); } catch (Exception e) { }
    }

    private int nextInt(String message) {
        System.out.print(message);
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("You did not enter an integer.");
            scanner.next();
            return nextInt(message);
        }
    }

    public int nextIntRange(String message, int min, int max) {
        int response = nextInt(message);
        while (response < min || response > max) {
            System.out.println("Number must be between " + min + " and " + max + " inclusive.");
            return nextIntRange(message, min, max);
        }
        return response;
    }

    public String nextString(String message) {
        System.out.print(message);
        return scanner.next();
    }

    public String nextStringExpect(String message,  List<String> expected) {
        System.out.print(message + " ");
        int expectedSize = expected.size();
        for (int i = 0; i < expectedSize; ++i) {
            System.out.print(expected.get(i) + ((i+1 < expectedSize) ? "/" : " "));
        }
        String response = scanner.next();
        while (!expected.contains(response)) {
            return nextStringExpect(message, expected);
        }
        return response;
    }
}