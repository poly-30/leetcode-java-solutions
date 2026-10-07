import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int nearestExit(char[][] maze, int[] entrance) {
        int rows = maze.length;
        int cols = maze[0].length;
        
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{entrance[0], entrance[1], 0});
        
        maze[entrance[0]][entrance[1]] = '+';
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];
            int steps = current[2];
            
            for (int[] dir : directions) {
                int nextR = r + dir[0];
                int nextC = c + dir[1];
                
                if (nextR >= 0 && nextR < rows && nextC >= 0 && nextC < cols && maze[nextR][nextC] == '.') {
                    
                    if (nextR == 0 || nextR == rows - 1 || nextC == 0 || nextC == cols - 1) {
                        return steps + 1;
                    }
                    
                    maze[nextR][nextC] = '+';
                    queue.offer(new int[]{nextR, nextC, steps + 1});
                }
            }
        }
        
        return -1;
    }
}
