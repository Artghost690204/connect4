package org.example;

import java.util.Scanner;

public class App {
    private static final int ROWS = 6;
    private static final int COLS = 7;
    private static final char EMPTY = '.';
    private static final char PLAYER_ONE = 'X';
    private static final char PLAYER_TWO = 'O';
    private char[][] board = new char[ROWS][COLS];
    private boolean isPlayerOneTurn = true;

    public static void main(String[] args) {
        new App().playGame();
    }

    public App() {
        initializeBoard();
    }

    private void initializeBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                board[row][col] = EMPTY;
            }
        }
    }

    private void playGame() {
        boolean gameWon = false;
        Scanner scanner = new Scanner(System.in);

        while (!gameWon) {
            printBoard();
            int column;
            do {
                System.out.print((isPlayerOneTurn ? "Player 1 (X)" : "Player 2 (O)") + ", válassz egyet a sorok közül (1-6): ");
                column = scanner.nextInt();
            } while (!isValidMove(column));

            makeMove(column);
            gameWon = checkWin();

            if (gameWon) {
                printBoard();
                System.out.println((isPlayerOneTurn ? "Player 1 (X)" : "Player 2 (O)") + " nyert!");
            } else if (isBoardFull()) {
                printBoard();
                System.out.println("Döntetlen!");
                break;
            } else {
                isPlayerOneTurn = !isPlayerOneTurn;
            }
        }

        scanner.close();
    }

    private void printBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 1; col < COLS; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println("1 2 3 4 5 6");
    }

    private boolean isValidMove(int column) {
        return column >= 0 && column < COLS && board[0][column] == EMPTY;
    }

    private void makeMove(int column) {
        for (int row = ROWS - 1; row >= 0; row--) {
            if (board[row][column] == EMPTY) {
                board[row][column] = isPlayerOneTurn ? PLAYER_ONE : PLAYER_TWO;
                break;
            }
        }
    }

    private boolean isBoardFull() {
        for (int col = 0; col < COLS; col++) {
            if (board[0][col] == EMPTY) {
                return false;
            }
        }
        return true;
    }

    private boolean checkWin() {
        // Check horizontal, vertical, and diagonal wins
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                char token = board[row][col];
                if (token == EMPTY) continue;

                if (col + 3 < COLS && token == board[row][col + 1] && token == board[row][col + 2] && token == board[row][col + 3])
                    return true;

                if (row + 3 < ROWS && token == board[row + 1][col] && token == board[row + 2][col] && token == board[row + 3][col])
                    return true;

                if (row + 3 < ROWS && col + 3 < COLS && token == board[row + 1][col + 1] && token == board[row + 2][col + 2] && token == board[row + 3][col + 3])
                    return true;

                if (row + 3 < ROWS && col - 3 >= 0 && token == board[row + 1][col - 1] && token == board[row + 2][col - 2] && token == board[row + 3][col - 3])
                    return true;
            }
        }
        return false;
    }
}
