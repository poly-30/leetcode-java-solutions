class Solution {
    /**
     * Helper function to check if the sequence of numbers is valid.
     * The required sequence is 1, 2, 0, 2, 0, ...
     * This translates to the following valid transitions:
     * 1 -> 2
     * 2 -> 0
     * 0 -> 2
     */
    private boolean isValid(int prevVal, int currVal) {
        if (prevVal == 1 && currVal == 2) return true;
        if (prevVal == 2 && currVal == 0) return true;
        if (prevVal == 0 && currVal == 2) return true;
        return false;
    }

    public int lenOfVDiagonal(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int maxLen = 0;

        // === PART 1: Calculate rays ENDING at (r,c) that start with a 1 ===
        int[][] dp_end_tl_br = new int[n][m]; // from Top-Left
        int[][] dp_end_tr_bl = new int[n][m]; // from Top-Right
        int[][] dp_end_bl_tr = new int[n][m]; // from Bottom-Left
        int[][] dp_end_br_tl = new int[n][m]; // from Bottom-Right

        // from Top-Left to Bottom-Right
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (grid[r][c] == 1) {
                    dp_end_tl_br[r][c] = 1;
                } else if (r > 0 && c > 0 && dp_end_tl_br[r - 1][c - 1] > 0 && isValid(grid[r - 1][c - 1], grid[r][c])) {
                    dp_end_tl_br[r][c] = dp_end_tl_br[r - 1][c - 1] + 1;
                }
                maxLen = Math.max(maxLen, dp_end_tl_br[r][c]);
            }
        }
        // from Top-Right to Bottom-Left
        for (int r = 0; r < n; r++) {
            for (int c = m - 1; c >= 0; c--) {
                if (grid[r][c] == 1) {
                    dp_end_tr_bl[r][c] = 1;
                } else if (r > 0 && c < m - 1 && dp_end_tr_bl[r - 1][c + 1] > 0 && isValid(grid[r - 1][c + 1], grid[r][c])) {
                    dp_end_tr_bl[r][c] = dp_end_tr_bl[r - 1][c + 1] + 1;
                }
                maxLen = Math.max(maxLen, dp_end_tr_bl[r][c]);
            }
        }
        // from Bottom-Left to Top-Right
        for (int r = n - 1; r >= 0; r--) {
            for (int c = 0; c < m; c++) {
                if (grid[r][c] == 1) {
                    dp_end_bl_tr[r][c] = 1;
                } else if (r < n - 1 && c > 0 && dp_end_bl_tr[r + 1][c - 1] > 0 && isValid(grid[r + 1][c - 1], grid[r][c])) {
                    dp_end_bl_tr[r][c] = dp_end_bl_tr[r + 1][c - 1] + 1;
                }
                maxLen = Math.max(maxLen, dp_end_bl_tr[r][c]);
            }
        }
        // from Bottom-Right to Top-Left
        for (int r = n - 1; r >= 0; r--) {
            for (int c = m - 1; c >= 0; c--) {
                if (grid[r][c] == 1) {
                    dp_end_br_tl[r][c] = 1;
                } else if (r < n - 1 && c < m - 1 && dp_end_br_tl[r + 1][c + 1] > 0 && isValid(grid[r + 1][c + 1], grid[r][c])) {
                    dp_end_br_tl[r][c] = dp_end_br_tl[r + 1][c + 1] + 1;
                }
                maxLen = Math.max(maxLen, dp_end_br_tl[r][c]);
            }
        }

        // === PART 2: Calculate rays STARTING at (r,c) (as continuations) ===
        int[][] dp_start_tl_br = new int[n][m]; // to Bottom-Right
        int[][] dp_start_tr_bl = new int[n][m]; // to Bottom-Left
        int[][] dp_start_bl_tr = new int[n][m]; // to Top-Right
        int[][] dp_start_br_tl = new int[n][m]; // to Top-Left

        // to Bottom-Right
        for (int r = n - 1; r >= 0; r--) {
            for (int c = m - 1; c >= 0; c--) {
                dp_start_tl_br[r][c] = 1;
                if (r < n - 1 && c < m - 1 && isValid(grid[r][c], grid[r + 1][c + 1])) {
                    dp_start_tl_br[r][c] += dp_start_tl_br[r + 1][c + 1];
                }
            }
        }
        // to Bottom-Left
        for (int r = n - 1; r >= 0; r--) {
            for (int c = 0; c < m; c++) {
                dp_start_tr_bl[r][c] = 1;
                if (r < n - 1 && c > 0 && isValid(grid[r][c], grid[r + 1][c - 1])) {
                    dp_start_tr_bl[r][c] += dp_start_tr_bl[r + 1][c - 1];
                }
            }
        }
        // to Top-Right
        for (int r = 0; r < n; r++) {
            for (int c = m - 1; c >= 0; c--) {
                dp_start_bl_tr[r][c] = 1;
                if (r > 0 && c < m - 1 && isValid(grid[r][c], grid[r - 1][c + 1])) {
                    dp_start_bl_tr[r][c] += dp_start_bl_tr[r - 1][c + 1];
                }
            }
        }
        // to Top-Left
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                dp_start_br_tl[r][c] = 1;
                if (r > 0 && c > 0 && isValid(grid[r][c], grid[r - 1][c - 1])) {
                    dp_start_br_tl[r][c] += dp_start_br_tl[r - 1][c - 1];
                }
            }
        }

        // === PART 3: Combine rays at turning points to form V-shapes ===
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                // In: from TL (to BR), Out: to BL
                if (dp_end_tl_br[r][c] > 0 && r < n - 1 && c > 0 && isValid(grid[r][c], grid[r + 1][c - 1])) {
                    maxLen = Math.max(maxLen, dp_end_tl_br[r][c] + dp_start_tr_bl[r + 1][c - 1]);
                }
                // In: from TR (to BL), Out: to TL
                if (dp_end_tr_bl[r][c] > 0 && r > 0 && c > 0 && isValid(grid[r][c], grid[r - 1][c - 1])) {
                    maxLen = Math.max(maxLen, dp_end_tr_bl[r][c] + dp_start_br_tl[r - 1][c - 1]);
                }
                // In: from BR (to TL), Out: to TR
                if (dp_end_br_tl[r][c] > 0 && r > 0 && c < m - 1 && isValid(grid[r][c], grid[r - 1][c + 1])) {
                    maxLen = Math.max(maxLen, dp_end_br_tl[r][c] + dp_start_bl_tr[r - 1][c + 1]);
                }
                // In: from BL (to TR), Out: to BR
                if (dp_end_bl_tr[r][c] > 0 && r < n - 1 && c < m - 1 && isValid(grid[r][c], grid[r + 1][c + 1])) {
                    maxLen = Math.max(maxLen, dp_end_bl_tr[r][c] + dp_start_tl_br[r + 1][c + 1]);
                }
            }
        }

        return maxLen;
    }
}