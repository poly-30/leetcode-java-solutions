import java.util.*;

class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        // Step 1: Build Adjacency List
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] inv : invocations) {
            adj.get(inv[0]).add(inv[1]);
        }

        // Step 2: BFS to identify all suspicious methods starting from k
        boolean[] suspicious = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        
        suspicious[k] = true;
        queue.offer(k);

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int neighbor : adj.get(curr)) {
                if (!suspicious[neighbor]) {
                    suspicious[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }

        // Step 3: Check if any non-suspicious method calls a suspicious method
        for (int[] inv : invocations) {
            int u = inv[0];
            int v = inv[1];
            if (!suspicious[u] && suspicious[v]) {
                // Invalid removal: Return all methods [0, n - 1]
                List<Integer> allMethods = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    allMethods.add(i);
                }
                return allMethods;
            }
        }

        // Step 4: Collect all non-suspicious methods
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!suspicious[i]) {
                result.add(i);
            }
        }

        return result;
    }
}