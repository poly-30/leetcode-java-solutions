class Solution {
   
    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        solve(board);
    }
    private boolean solve(char[][] board) {
        // Iterate through each cell of the board
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                
                // Find an empty cell ('.')
                if (board[row][col] == '.') {
                    
                    // Try placing numbers '1' through '9'
                    for (char num = '1'; num <= '9'; num++) {
                        
                        // Check if the current number is valid in this position
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num; // Place the number

                            // Recursively try to solve the rest of the board
                            if (solve(board)) {
                                return true; // Solution found!
                            } else {
                                // Backtrack: undo the choice if it didn't lead to a solution
                                board[row][col] = '.'; 
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(char[][] board, int row, int col, char num) {
        // Find the top-left corner of the 3x3 sub-grid
        int boxRowStart = 3 * (row / 3);
        int boxColStart = 3 * (col / 3);

        for (int i = 0; i < 9; i++) {
            // 1. Check if the number already exists in the current row
            if (board[row][i] == num) {
                return false;
            }
            
            // 2. Check if the number already exists in the current column
            if (board[i][col] == num) {
                return false;
            }
            
            // 3. Check if the number already exists in the 3x3 sub-grid
            int r = boxRowStart + i / 3;
            int c = boxColStart + i % 3;
            if (board[r][c] == num) {
                return false;
            }
        }
        // If all checks pass, the placement is valid
        return true;
    }
}