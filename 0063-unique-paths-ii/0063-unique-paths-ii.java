class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        // If starting cell or ending cell has an obstacle, return 0
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }

        int[] dp = new int[n];
        dp[0] = 1; // 1 path to start at (0, 0)

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (obstacleGrid[r][c] == 1) {
                    dp[c] = 0; // Obstacle blocks all incoming paths
                } else if (c > 0) {
                    dp[c] += dp[c - 1]; // paths from top + paths from left
                }
            }
        }

        return dp[n - 1];
    }
}