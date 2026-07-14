class Cell {
    int x;
    int y;
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    public void solve(char[][] board) {
        int[][] directions = new int[][] {{0,1}, {1,0}, {0,-1}, {-1,0}};

        int rows = board.length;
        int cols = board[0].length;

        Queue<Cell> queue = new LinkedList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 || j == 0 || i == rows - 1 || j == cols - 1) {
                    if (board[i][j] == 'O') {
                        queue.add(new Cell(i, j));
                        board[i][j] = 'B';
                    }
                }
            }
        }

        while (!queue.isEmpty()) {
            Cell cell = queue.poll();
            int x = cell.x;
            int y = cell.y;

            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];
                if (isValid(newX, newY, rows, cols) && board[newX][newY] == 'O') {
                    queue.add(new Cell(newX, newY));
                    board[newX][newY] = 'B';
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'B') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private boolean isValid(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}