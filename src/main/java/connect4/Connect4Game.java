package connect4;

import java.util.Scanner;

public class Connect4Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connect4Board board = new Connect4Board();
        RandomAI ai = new RandomAI();
        HighscoreManager highscoreManager = new HighscoreManager();

        System.out.println("Üdv a Connect 4 játékban!");
        System.out.println("Az első játékos te vagy (sárga), a második az AI (piros).");
        boolean isPlayerTurn = true;
        int winner = -1;

        while (!board.isFull()) {
            board.printBoard();
            if (isPlayerTurn) {
                System.out.print("Válassz oszlopot (1-7): ");
                int column = scanner.nextInt() - 1;
                if (board.makeMove(column, 1)) {
                    isPlayerTurn = false;
                } else {
                    System.out.println("Érvénytelen lépés, próbáld újra.");
                }
            } else {
                System.out.println("AI lép...");
                int aiMove = ai.makeMove(board.getBoard());
                board.makeMove(aiMove, 2);
                isPlayerTurn = true;
            }

            winner = board.checkWinner();
            if (winner != -1) break;
        }

        board.printBoard();
        if (winner == 1) {
            System.out.println("Gratulálok, nyertél!");
            highscoreManager.addHighscore("Player", 1);
        } else if (winner == 2) {
            System.out.println("Az AI nyert!");
            highscoreManager.addHighscore("AI", 1);
        } else {
            System.out.println("Döntetlen!");
        }

        System.out.println("Highscore-ok:");
        highscoreManager.loadScores();
        highscoreManager.getScores().forEach(System.out::println);
    }
}
