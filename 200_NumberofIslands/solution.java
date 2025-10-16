class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1') {
                    dfs(grid, r, c, rows, cols);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int r, int c, int rows, int cols) {
        if (r < 0 || c < 0 || r >= rows || c >= cols || grid[r][c] != '1') {
            return;
        }
        grid[r][c] = '0'; 
        dfs(grid, r - 1, c, rows, cols);
        dfs(grid, r + 1, c, rows, cols); 
        dfs(grid, r, c - 1, rows, cols); 
        dfs(grid, r, c + 1, rows, cols); 
    }
}
