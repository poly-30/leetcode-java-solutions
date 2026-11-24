class Solution {
    public void solve(char[][] board) {
        int rows = board.length;
        if (rows < 3) return;
        int cols = board[0].length;
        if (cols < 3) return;

        int[] queue = new int[rows * cols];
        int head = 0, tail = 0;

        for (int j = 0; j < cols; j++) {
            if (board[0][j] == 'O') { board[0][j] = 'B'; queue[tail++] = j; }
            if (board[rows - 1][j] == 'O') { board[rows - 1][j] = 'B'; queue[tail++] = (rows - 1) * cols + j; }
        }
        for (int i = 1; i < rows - 1; i++) {
            if (board[i][0] == 'O') { board[i][0] = 'B'; queue[tail++] = i * cols; }
            if (board[i][cols - 1] == 'O') { board[i][cols - 1] = 'B'; queue[tail++] = i * cols + cols - 1; }
        }

        while (head < tail) {
            int cur = queue[head++];
            int x = cur / cols;
            int y = cur % cols;

            if (x > 0 && board[x - 1][y] == 'O') { board[x - 1][y] = 'B'; queue[tail++] = cur - cols; }
            if (x < rows - 1 && board[x + 1][y] == 'O') { board[x + 1][y] = 'B'; queue[tail++] = cur + cols; }
            if (y > 0 && board[x][y - 1] == 'O') { board[x][y - 1] = 'B'; queue[tail++] = cur - 1; }
            if (y < cols - 1 && board[x][y + 1] == 'O') { board[x][y + 1] = 'B'; queue[tail++] = cur + 1; }
        }

        for (int i = 0; i < rows; i++) {
            char[] row = board[i];
            for (int j = 0; j < cols; j++) {
                if (row[j] == 'B') row[j] = 'O';
                else if (row[j] == 'O') row[j] = 'X';
            }
        }
    }
}