class Solution {
    // Define the valid directions and which street types open up to them.
    // Directions: 0 -> Left, 1 -> Right, 2 -> Up, 3 -> Down
    private static final int[][] DIRS = {
        {0, -1}, // Left
        {0, 1},  // Right
        {-1, 0}, // Up
        {1, 0}   // Down
    };

    // Which directions each street type allows movement into:
    // Street 1: Left, Right
    // Street 2: Up, Down
    // Street 3: Left, Down
    // Street 4: Right, Down
    // Street 5: Left, Up
    // Street 6: Right, Up
    private static final int[][] STREET_DIRS = {
        {},
        {0, 1},
        {2, 3},
        {0, 3},
        {1, 3},
        {0, 2},
        {1, 2}
    };

    public boolean hasValidPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        
        return dfs(0, 0, grid, visited, m, n);
    }

    private boolean dfs(int r, int c, int[][] grid, boolean[][] visited, int m, int n) {
        // If we reached the bottom-right cell, return true
        if (r == m - 1 && c == n - 1) {
            return true;
        }

        visited[r][c] = true;
        int currentStreet = grid[r][c];

        for (int d : STREET_DIRS[currentStreet]) {
            int nextR = r + DIRS[d][0];
            int nextC = c + DIRS[d][1];

            // Check boundaries and if the next cell is already visited
            if (nextR >= 0 && nextR < m && nextC >= 0 && nextC < n && !visited[nextR][nextC]) {
                // Check if the neighbor connects back to our current cell
                if (connectsBack(nextR, nextC, d, grid)) {
                    if (dfs(nextR, nextC, grid, visited, m, n)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean connectsBack(int r, int c, int incomingDir, int[][] grid) {
        int neighborStreet = grid[r][c];
        // The opposite direction we came from
        int requiredDir = (incomingDir % 2 == 0) ? incomingDir + 1 : incomingDir - 1;

        for (int d : STREET_DIRS[neighborStreet]) {
            if (d == requiredDir) {
                return true;
            }
        }
        return false;
    }
}