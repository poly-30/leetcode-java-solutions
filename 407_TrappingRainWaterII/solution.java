import java.util.PriorityQueue;

class Solution {
    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0) return 0;

        int rows = heightMap.length, cols = heightMap[0].length;
        boolean[][] visited = new boolean[rows][cols];
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]); // [height, row, col]

        // Add all boundary cells to the heap
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 || i == rows - 1 || j == 0 || j == cols - 1) {
                    minHeap.offer(new int[]{heightMap[i][j], i, j});
                    visited[i][j] = true;
                }
            }
        }

        int totalWater = 0;
        int[] directions = {-1, 0, 1, 0, -1};

        while (!minHeap.isEmpty()) {
            int[] cell = minHeap.poll();
            int height = cell[0], x = cell[1], y = cell[2];

            for (int k = 0; k < 4; k++) {
                int nx = x + directions[k], ny = y + directions[k + 1];
                if (nx >= 0 && nx < rows && ny >= 0 && ny < cols && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    // Water trapped is difference between cell height and boundary; must be non-negative
                    totalWater += Math.max(0, height - heightMap[nx][ny]);
                    // The boundary for the next cell is max(current boundary, next cell's height)
                    minHeap.offer(new int[]{Math.max(height, heightMap[nx][ny]), nx, ny});
                }
            }
        }

        return totalWater;
    }
}
