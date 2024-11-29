package connect4;

import java.util.Arrays;

public class Connect4Board {
    private final int[][] board = new int[6][7];

    public boolean makeMove(int column, int player) {
        if (column < 0 || column >= 7 || board[0][column] != 0) {
            return false;
        }
        for (int row = 5; row >= 0; row--) {
            if (board[row][column] == 0) {
                board[row][column] = player;
                return true;
            }
        }
        return false;
    }

    public int checkWinner() {
        // Ellenőrzi a győzelmet (sorok, oszlopok, átlók).
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                int player = board[row][col];
                if (player != 0) {
                    if (checkDirection(row, col, 1, 0, player) || // sor
                            checkDirection(row, col, 0, 1, player) || // oszlop
                            checkDirection(row, col, 1, 1, player) || // átló /
                            checkDirection(row, col, 1, -1, player)) { // átló \
                        return player;
                    }
                }
            }
        }
        return -1; // Nincs győztes.
    }

    private boolean checkDirection(int row, int col, int dRow, int dCol, int player) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int r = row + dRow * i, c = col + dCol * i;
            if (r >= 0 && r < 6 && c >= 0 && c < 7 && board[r][c] == player) {
                count++;
            } else {
                break;
            }
        }
        return count == 4;
    }

    public boolean isFull() {
        return Arrays.stream(board[0]).allMatch(cell -> cell != 0);
    }

    public void printBoard() {
        for (int[] row : board) {
            for (int cell : row) {
                System.out.print(cell == 1 ? "Y " : cell == 2 ? "R " : ". ");
            }
            System.out.println();
        }
    }

    public int[][] getBoard() {
        return board;
    }
}
