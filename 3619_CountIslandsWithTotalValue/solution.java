class Solution {
    int m, n;
    boolean[][] visited;
    int[][] grid;
    int k;
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};
    public int countIslands(int[][] grid, int k){
        this.grid = grid;
        this.k = k;
        this.m = grid.length;
        this.n = grid[0].length;
        this.visited = new boolean[m][n];
        int islandsCount = 0;
        for(int r = 0; r < m; ++r){
            for(int c = 0; c < n; ++c){
                if(grid[r][c] > 0 && ! visited[r][c]){
                    long[] sumHolder = {0};
                    dfs(r, c, sumHolder);
                    if(sumHolder[0] % k == 0){
                        islandsCount++;
                    }
                }
            }
        }
        return islandsCount;
    }
    private void dfs(int r, int c, long[] sumHolder){
        if(r < 0|| r >= m || c < 0 || c >= n){
            return;
        }
        if(grid[r][c] == 0 || visited[r][c]){
            return ;
        }
        visited[r][c] = true;
        sumHolder[0] += grid[r][c];
        for(int i = 0; i < 4; ++i){
            dfs(r + dr[i], c + dc[i], sumHolder);
        }
    }
}