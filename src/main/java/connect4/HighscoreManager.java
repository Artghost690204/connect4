package connect4;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class HighscoreManager {
    private final Map<String, Integer> scores = new HashMap<>();

    public void addWin(String playerName) {
        scores.put(playerName, scores.getOrDefault(playerName, 0) + 1);
        saveScores();
    }

    private void saveScores() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("highscores.txt"))) {
            for (Map.Entry<String, Integer> entry : scores.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadScores() {
        scores.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("highscores.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String playerName = parts[0].trim(); // Szóközök eltávolítása a névből
                    int score = Integer.parseInt(parts[1].trim()); // Szóközök eltávolítása a pontszámból
                    scores.put(playerName, score);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Map<String, Integer> getScores() {
        return scores;
    }

    public void printScores() {
        System.out.println("Highscores:");
        scores.forEach((player, wins) -> System.out.println(player + ": " + wins + " wins"));
    }
}
