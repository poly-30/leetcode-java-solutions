import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        
        int startR = -1, startC = -1;
        List<int[]> litters = new ArrayList<>();
        
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                char ch = classroom[r].charAt(c);
                if (ch == 'S') {
                    startR = r;
                    startC = c;
                } else if (ch == 'L') {
                    litters.add(new int[]{r, c});
                }
            }
        }
        
        int k = litters.size();
        int fullMask = (1 << k) - 1;
        
        // Track maximum energy achieved for state (r, c, mask)
        int[][][] maxEnergy = new int[m][n][1 << k];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(maxEnergy[i][j], -1);
            }
        }
        
        Queue<int[]> queue = new LinkedList<>();
        
        // Initial state mask check if starting cell is 'L' (though problem states 'S')
        int initialMask = 0;
        for (int i = 0; i < k; i++) {
            if (litters.get(i)[0] == startR && litters.get(i)[1] == startC) {
                initialMask |= (1 << i);
            }
        }
        
        queue.offer(new int[]{startR, startC, initialMask, energy});
        maxEnergy[startR][startC][initialMask] = energy;
        
        int steps = 0;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int r = curr[0], c = curr[1], mask = curr[2], currEnergy = curr[3];
                
                if (mask == fullMask) {
                    return steps;
                }
                
                if (currEnergy == 0) continue;
                
                for (int[] dir : dirs) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    
                    if (nr >= 0 && nr < m && nc >= 0 && nc < n && classroom[nr].charAt(nc) != 'X') {
                        int nextEnergy = currEnergy - 1;
                        char nextCell = classroom[nr].charAt(nc);
                        
                        if (nextCell == 'R') {
                            nextEnergy = energy;
                        }
                        
                        int nextMask = mask;
                        if (nextCell == 'L') {
                            for (int idx = 0; idx < k; idx++) {
                                if (litters.get(idx)[0] == nr && litters.get(idx)[1] == nc) {
                                    nextMask |= (1 << idx);
                                    break;
                                }
                            }
                        }
                        
                        if (nextEnergy > maxEnergy[nr][nc][nextMask]) {
                            maxEnergy[nr][nc][nextMask] = nextEnergy;
                            queue.offer(new int[]{nr, nc, nextMask, nextEnergy});
                        }
                    }
                }
            }
            steps++;
        }
        
        return -1;
    }
}