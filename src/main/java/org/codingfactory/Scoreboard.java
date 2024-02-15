package org.codingfactory;

import java.io.*;
import java.util.*;

public class Scoreboard {
    private Map<String, Integer> scores = new HashMap<>();

    private static final int WIN_POINTS = 5;
    private static final int LOSE_POINTS = -2;
    private static final int TOP_SCORES = 10;

    /**
     * Check if the scoreboard is empty.
     */
    public boolean isEmpty() {
        return scores.isEmpty();
    }

    /**
     * Save the scores hashmap in a binary file
     */
    public void save() {
        try (FileOutputStream fos = new FileOutputStream("scores.bin");
             ObjectOutputStream oos = new ObjectOutputStream(fos))
        {
            oos.writeObject(scores);
        } catch (IOException e) {
            System.err.println("An error occurred when saving the scores: " + e.getMessage());
        }
    }

    public void load() {
        try (FileInputStream fis = new FileInputStream("scores.bin");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            scores = (Map<String, Integer>)ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("An error occurred when loading the scores: " + e.getMessage());
        }
    }

    /**
     * Display the top 10 scores in ascending or descending order.
     */
    public void displayScoresSorted(boolean descending) {
        // Sort scores
        var sortedScores = new ArrayList<>(scores.entrySet());
        sortedScores.sort(descending ?
            Map.Entry.comparingByValue() :
            Collections.reverseOrder(Map.Entry.comparingByValue()));

        // Display the top 10
        var topScores = sortedScores.subList(0, Math.min(sortedScores.size(), 9));
        for (var score : topScores) {
            System.out.println("Pseudo : " + score.getKey() + ", Score : " + score.getValue());
        }
    }

    /**
     * Display the top 10 scores, alpha-numerically sorted.
     */
    public void displayScores() {
        var scores = new ArrayList<>(this.scores.entrySet());
        scores.sort(Map.Entry.comparingByKey());
        var topScores = scores.subList(0, Math.min(scores.size(), TOP_SCORES - 1));
        for (var score : topScores) {
            System.out.println("Pseudo : " + score.getKey() + ", Score : " + score.getValue());
        }
    }

    /**
     * Create or update the score of a player.
     * @param pseudo Player's name.
     * @param wonRound If he won the round, he earns 5 points, else he loose 2 points.
     */
    public void updateScore(String pseudo, boolean wonRound) {
        var playerScore = scores.getOrDefault(pseudo, 0);
        if (wonRound) {
            scores.put(pseudo, playerScore + WIN_POINTS);
        } else {
            scores.put(pseudo, playerScore + LOSE_POINTS);
        }
    }
}

