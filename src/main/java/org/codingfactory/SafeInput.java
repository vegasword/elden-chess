package org.codingfactory;

import java.util.*;

public class SafeInput {

    private Scanner scanner;

    public SafeInput() {
        scanner = new Scanner(System.in);
    }

    public void next() {
        scanner.next();
    }

    private int nextInt(String message) {
        System.out.println(message);
        int response = 0;
        try {
            response = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("You did not enter an integer.");
            scanner.next();
            return nextInt(message);
        }
        return response;
    }

    public int nextIntRange(String message, int min, int max) {
        int response = nextInt(message);
        while (response < min || response > max) {
            System.out.println("Number must be between " + min + " and " + max + " inclusive.");
            response = nextInt(message);
        }
        return response;
    }

    public String nextString(String message) {
        System.out.println(message);
        return scanner.next();
    }

    public String nextStringExpect(String message, List<String> expected) {
        message += " (" + expected + ")";
        System.out.println(message);
        String response = scanner.next();
        while (!expected.contains(response)) {
            System.out.println(message);
            response = scanner.next();
        }
        return response;
    }

    public boolean nextYesNoAnswer () {
        String response = scanner.next();
        if (response.contains("y") || response.contains("Y")) {
            return true;
        } else if (response.contains("n") || response.contains("N")) {
            return false;
        } else {
            System.out.println("Yes/no response required. (y/n, Yeah, Yep, Nope, No, Nein)");
            return nextYesNoAnswer();
        }
    }

    public void clearScreen () {
        String systemInfo = System.getProperty("os.name");
        if (systemInfo.contains("Windows")) {
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (Exception exception) {}
        } else if (systemInfo.contains("Mac")) {
            System.out.print("\033[H\033[2J");
        } else {
            System.out.println("Unable to determine system info. Unable to clear screen.");
        }
    }

    public String fixedLengthString (String input, int length) {
        if (input.length() > length) {
            return input.substring(0, length);
        }
        while (input.length() < length) {
            input += " ";
        }
        return input;
    }
}