package connect4;

import java.util.Random;

public class RandomAI {
    private final Random random = new Random();

    public int makeMove(int[][] board) {
        int column;
        do {
            column = random.nextInt(7);
        } while (board[0][column] != 0);
        return column;
    }
}
