package org.codingfactory;

import java.util.*;

public class Scoreboard {
    private Map<String, Integer> scores = new HashMap<>();

    /**
     * Check if the scoreboard is empty.
     */
    public boolean isEmpty() {
        return scores.isEmpty();
    }

    /**
     * Display the top 10 scores in ascending order.
     */
    public void displayScoresAsc() {
        // Sort scores
        var sortedScores = new ArrayList<>(scores.entrySet());
        sortedScores.sort(Map.Entry.comparingByValue());

        // Display the top 10
        var topScores = sortedScores.subList(0, 9);
        for (var score : topScores) {
            System.out.println("Pseudo : " + score.getKey() + ", Score : " + score.getValue());
        }
    }

   /**
     * Display the top 10 scores in ascending order.
     */
   public void displayScoresDesc() {
        // Sort scores
        var sortedScores = new ArrayList<>(scores.entrySet());
        sortedScores.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));

        // Display the top 10
        var topScores = sortedScores.subList(0, 9);
        for (var score : topScores) {
            System.out.println("Pseudo : " + score.getKey() + ", Score : " + score.getValue());
        }
    }

    /**
     * Display the top 10 scores, alpha-numerically sorted.
     */
    public void displayScores() {
        var scores = new ArrayList<>(this.scores.entrySet());
        scores.subList(0, 10);
        for (var score : scores) {
            System.out.println("Pseudo : " + score.getKey() + ", Score : " + score.getValue());
        }
    }

    /**
     * Create or update the score of a player.
     * @param pseudo Player's name.
     * @param wonRound If he won the round, he earns 5 points, else he loose 2 points.
     */
    public void updateScore(String pseudo, boolean wonRound) {
        var playerScore = scores.get(pseudo);
        if (playerScore == null) {
            scores.put(pseudo, wonRound ? 5 : -2);
        } else {
            scores.put(pseudo, wonRound ? playerScore + 5 : playerScore - 2);
        }
    }
}
