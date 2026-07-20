class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int totalElements = m * n;
        k %= totalElements;
        
        int[][] ans = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int new1DIndex = (i * n + j + k) % totalElements;
                int newRow = new1DIndex / n;
                int newCol = new1DIndex % n;
                ans[newRow][newCol] = grid[i][j];
            }
        }
        
        // Convert 2D primitive array to the required List<List<Integer>> output format
        List<List<Integer>> result = new ArrayList<>();
        for (int[] row : ans) {
            List<Integer> listRow = new ArrayList<>();
            for (int val : row) listRow.add(val);
            result.add(listRow);
        }
        return result;
    }
}
