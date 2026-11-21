import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            adj.get(parent[i]).add(i);
        }

        int[] newParent = Arrays.copyOf(parent, n);
        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        // Pass 1: Determine simultaneous new parents via DFS + backtracking
        findNewParents(0, adj, s, lastSeen, newParent);

        // Build the modified tree
        List<List<Integer>> newAdj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            newAdj.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            newAdj.get(newParent[i]).add(i);
        }

        // Pass 2: Calculate subtree sizes on the final tree
        int[] ans = new int[n];
        computeSizes(0, newAdj, ans);
        return ans;
    }

    private void findNewParents(int u, List<List<Integer>> adj, String s, int[] lastSeen, int[] newParent) {
        int charIdx = s.charAt(u) - 'a';
        if (lastSeen[charIdx] != -1) {
            newParent[u] = lastSeen[charIdx];
        }

        int prevAncestor = lastSeen[charIdx];
        lastSeen[charIdx] = u;

        for (int v : adj.get(u)) {
            findNewParents(v, adj, s, lastSeen, newParent);
        }

        // Backtrack so sibling branches don't see u as an ancestor
        lastSeen[charIdx] = prevAncestor;
    }

    private int computeSizes(int u, List<List<Integer>> newAdj, int[] ans) {
        int size = 1;
        for (int v : newAdj.get(u)) {
            size += computeSizes(v, newAdj, ans);
        }
        ans[u] = size;
        return size;
    }
}