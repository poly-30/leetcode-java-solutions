import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

class Solution {
    private List<List<Integer>> adj;
    private int[] nums;
    private int[] xorValues;
    private int[] startTime;
    private int[] endTime;
    private int timer;

    public int minimumScore(int[] nums, int[][] edges) {
        int n = nums.length;
        this.nums = nums;
        
        
        this.adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

    
        this.xorValues = new int[n];
        this.startTime = new int[n];
        this.endTime = new int[n];
        this.timer = 0;
        dfs(0, -1);

        int minScore = Integer.MAX_VALUE;
        int totalXor = xorValues[0];

        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int xor1, xor2, xor3;

                boolean iIsAncestorOfJ = startTime[i] < startTime[j] && endTime[j] < endTime[i];
                boolean jIsAncestorOfI = startTime[j] < startTime[i] && endTime[i] < endTime[j];

                if (iIsAncestorOfJ) {
                
                    xor1 = xorValues[j];
                    xor2 = xorValues[i] ^ xorValues[j];
                    xor3 = totalXor ^ xorValues[i];
                } else if (jIsAncestorOfI) {
                
                    xor1 = xorValues[i];
                    xor2 = xorValues[j] ^ xorValues[i];
                    xor3 = totalXor ^ xorValues[j];
                } else {
                
                    xor1 = xorValues[i];
                    xor2 = xorValues[j];
                    xor3 = totalXor ^ xorValues[i] ^ xorValues[j];
                }

            
                int maxVal = Math.max(xor1, Math.max(xor2, xor3));
                int minVal = Math.min(xor1, Math.min(xor2, xor3));
                minScore = Math.min(minScore, maxVal - minVal);
            }
        }

        return minScore;
    }

    private void dfs(int u, int p) {
        startTime[u] = timer++;
        xorValues[u] = nums[u];
        
        for (int v : adj.get(u)) {
            if (v != p) {
                dfs(v, u);
                xorValues[u] ^= xorValues[v];
            }
        }
        endTime[u] = timer++;
    }
}