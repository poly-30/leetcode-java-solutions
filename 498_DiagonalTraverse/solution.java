class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        if (mat == null || mat.length == 0) {
            return new int[0];
        }

        int m = mat.length;
        int n = mat[0].length;
        int[] result = new int[m * n];
        int r = 0, c = 0;
        boolean up = true; // true for up-right, false for down-left

        for (int i = 0; i < m * n; i++) {
            result[i] = mat[r][c];

            if (up) {
                // Determine the next move for up-right direction
                if (c == n - 1) { // Hit right edge
                    r++;
                    up = false;
                } else if (r == 0) { // Hit top edge
                    c++;
                    up = false;
                } else { // Normal up-right move
                    r--;
                    c++;
                }
            } else { // Down-left
                // Determine the next move for down-left direction
                if (r == m - 1) { // Hit bottom edge
                    c++;
                    up = true;
                } else if (c == 0) { // Hit left edge
                    r++;
                    up = true;
                } else { // Normal down-left move
                    r++;
                    c--;
                }
            }
        }
        return result;
    }
}