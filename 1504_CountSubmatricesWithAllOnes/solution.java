class Solution {
    public int numSubmat(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int ans = 0;

        // Count consecutive 1s horizontally for each row
        int[][] horiz = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (mat[i][j] == 1) {
                    horiz[i][j] = (j == 0 ? 1 : horiz[i][j - 1] + 1);
                }
            }
        }

        // For each cell, look upwards to count rectangles
        for (int j = 0; j < n; ++j) {
            for (int i = 0; i < m; ++i) {
                int minWidth = horiz[i][j];
                for (int k = i; k >= 0 && minWidth > 0; --k) {
                    minWidth = Math.min(minWidth, horiz[k][j]);
                    ans += minWidth;
                }
            }
        }
        return ans;
    }
}