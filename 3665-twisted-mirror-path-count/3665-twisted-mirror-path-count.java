class Solution {
    private static final int MOD = 1_000_000_007;

    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][][] jump = new int[m][n][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                jump[i][j][0] = -1;
                jump[i][j][1] = -1;
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    getJumpTarget(i, j, 0, grid, jump, m, n);
                    getJumpTarget(i, j, 1, grid, jump, m, n);
                }
            }
        }

        long[][] dp = new long[m][n];
        dp[0][0] = 1;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (dp[r][c] == 0) continue;

                long ways = dp[r][c];

                if (c + 1 < n) {
                    if (grid[r][c + 1] == 0) {
                        dp[r][c + 1] = (dp[r][c + 1] + ways) % MOD;
                    } else {
                        int code = getJumpTarget(r, c + 1, 0, grid, jump, m, n);
                        if (code != -2) {
                            int nr = code / n;
                            int nc = code % n;
                            dp[nr][nc] = (dp[nr][nc] + ways) % MOD;
                        }
                    }
                }

                if (r + 1 < m) {
                    if (grid[r + 1][c] == 0) {
                        dp[r + 1][c] = (dp[r + 1][c] + ways) % MOD;
                    } else {
                        int code = getJumpTarget(r + 1, c, 1, grid, jump, m, n);
                        if (code != -2) {
                            int nr = code / n;
                            int nc = code % n;
                            dp[nr][nc] = (dp[nr][nc] + ways) % MOD;
                        }
                    }
                }
            }
        }

        return (int) dp[m - 1][n - 1];
    }

    private int getJumpTarget(int r, int c, int dir, int[][] grid, int[][][] jump, int m, int n) {
        if (r < 0 || r >= m || c < 0 || c >= n) {
            return -2;
        }

        if (grid[r][c] == 0) {
            return r * n + c;
        }

        if (jump[r][c][dir] != -1) {
            return jump[r][c][dir];
        }

        int nextR = r;
        int nextC = c;
        int nextDir = dir;

        if (dir == 0) {
            nextR = r + 1;
            nextDir = 1;
        } else {
            nextC = c + 1;
            nextDir = 0;
        }

        int target = getJumpTarget(nextR, nextC, nextDir, grid, jump, m, n);
        jump[r][c][dir] = target;
        return target;
    }
}