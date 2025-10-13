class Solution {
    private int[][] heights;
    private int rows;
    private int cols;

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        rows = heights.length;
        cols = heights[0].length;
        this.heights = heights;

        Deque<int[]> pacificQueue = new LinkedList<>();
        Deque<int[]> atlanticQueue = new LinkedList<>();
        Set<Integer> pacificVisited = new HashSet<>();
        Set<Integer> atlanticVisited = new HashSet<>();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (row == 0 || col == 0) {
                    int pos = row * cols + col;
                    pacificVisited.add(pos);
                    pacificQueue.offer(new int[] {row, col});
                }
                if (row == rows - 1 || col == cols - 1) {
                    int pos = row * cols + col;
                    atlanticVisited.add(pos);
                    atlanticQueue.offer(new int[] {row, col});
                }
            }
        }

        bfs(pacificQueue, pacificVisited);
        bfs(atlanticQueue, atlanticVisited);

        List<List<Integer>> result = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int pos = row * cols + col;
                if (pacificVisited.contains(pos) && atlanticVisited.contains(pos)) {
                    result.add(Arrays.asList(row, col));
                }
            }
        }
        return result;
    }

    private void bfs(Deque<int[]> queue, Set<Integer> visited) {
        int[] directions = {-1, 0, 1, 0, -1};
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1];
            for (int d = 0; d < 4; d++) {
                int nr = r + directions[d];
                int nc = c + directions[d+1];
                int nextPos = nr * cols + nc;
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols &&
                    !visited.contains(nextPos) && heights[nr][nc] >= heights[r][c]) {
                    visited.add(nextPos);
                    queue.offer(new int[] {nr, nc});
                }
            }
        }
    }
}