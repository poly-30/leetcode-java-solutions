import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public int[][] sortMatrix(int[][] grid) {
        int n = grid.length;
        for (int startRow = 0; startRow < n; startRow++) {
            List<Integer> diagonal = new ArrayList<>();
            for (int i = startRow, j = 0; i < n && j < n; i++, j++) {
                diagonal.add(grid[i][j]);
            }
            Collections.sort(diagonal, Collections.reverseOrder());
            int index = 0;
            for (int i = startRow, j = 0; i < n && j < n; i++, j++) {
                grid[i][j] = diagonal.get(index++);
            }
        }
        for (int startCol = 1; startCol < n; startCol++) {
            List<Integer> diagonal = new ArrayList<>();
            for (int i = 0, j = startCol; i < n && j < n; i++, j++) {
                diagonal.add(grid[i][j]);
            }
            Collections.sort(diagonal);
            int index = 0;
            for (int i = 0, j = startCol; i < n && j < n; i++, j++) {
                grid[i][j] = diagonal.get(index++);
            }
        }
        
        return grid;
    }
}