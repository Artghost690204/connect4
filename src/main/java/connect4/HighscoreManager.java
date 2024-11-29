package connect4;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HighscoreManager {
    private final List<String> scores = new ArrayList<>();

    public void addHighscore(String playerName, int score) {
        scores.add(playerName + ": " + score);
        saveScores();
    }

    private void saveScores() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("highscores.txt"))) {
            for (String score : scores) {
                writer.write(score);
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
                scores.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getScores() {
        return scores;
    }
}
