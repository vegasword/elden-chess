package org.codingfactory;
import java.util.*;
public class ScoreBoard {
    Map<String, Integer> score = new HashMap<>();

    ScoreBoard() {
        updateScore("A", true);
        updateScore("A", true);
        updateScore("A", true);
        updateScore("B", true);
        updateScore("B", false);
        updateScore("C", false);
        updateScore("D", false);
        updateScore("E", false);
        updateScore("F", false);
        updateScore("G", false);
        updateScore("H", false);
        updateScore("I", false);
        updateScore("J", false);
        updateScore("K", false);
        updateScore("L", false);
        updateScore("M", false);
        updateScore("N", false);
    }

    public void displayScoresAsc() {
        var scores = new ArrayList<>(score.entrySet());
        Collections.sort(scores, Map.Entry.comparingByValue());
        int length = scores.size();
        if (length >= 10) length = 10;
        for (int i = 0; i < length; i++) {
            System.out.println("Pseudo : " + scores.get(i).getKey() + ", Score : " + scores.get(i).getValue());
        }
    }

    public void displayScoresDesc() {
        var scores = new ArrayList<>(score.entrySet());
        Collections.sort(scores, Collections.reverseOrder(Map.Entry.comparingByValue()));
        int length = scores.size();
        if (length >= 10) length = 10;
        for (int i = 0; i < length; i++) {
            System.out.println("Pseudo : " + scores.get(i).getKey() + ", Score : " + scores.get(i).getValue());
        }
    }

    public void displayScores() {
        var scores = new ArrayList<>(score.entrySet());
        int length = score.entrySet().toArray().length;
        if (length >= 10) length = 10;
        for (int i = 0; i < length; i++) {
            System.out.println("Pseudo : " + scores.get(i).getKey() + ", Score : " + scores.get(i).getValue());
        }
    }

    public void updateScore(String pseudo, boolean wonRound) {
        var playerScore = score.get(pseudo);
        if (playerScore == null) {
            score.put(pseudo, wonRound ? 5 : -2);
        } else {
            score.put(pseudo, wonRound ? playerScore + 5 : playerScore - 2);
        }
    }
}
