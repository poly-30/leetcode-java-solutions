import java.util.*;

class Solution {
    int n, timer;
    List<List<Integer>> adj;
    int[] parent, depth, heavy, head, pos, sz;
    int[] tree;
    char[] sChars;

    private int dfs(int u, int p, int d) {
        depth[u] = d;
        parent[u] = p;
        sz[u] = 1;
        int maxSub = 0, heavyChild = -1;
        for (int v : adj.get(u)) {
            if (v != p) {
                int sub = dfs(v, u, d + 1);
                sz[u] += sub;
                if (sub > maxSub) {
                    maxSub = sub;
                    heavyChild = v;
                }
            }
        }
        heavy[u] = heavyChild;
        return sz[u];
    }

    private void decompose(int u, int h) {
        head[u] = h;
        pos[u] = ++timer;
        if (heavy[u] != -1) {
            decompose(heavy[u], h);
        }
        for (int v : adj.get(u)) {
            if (v != parent[u] && v != heavy[u]) {
                decompose(v, v);
            }
        }
    }

    private void updateTree(int node, int start, int end, int idx, int val) {
        if (start == end) {
            tree[node] = val;
            return;
        }
        int mid = (start + end) / 2;
        if (idx <= mid) updateTree(2 * node, start, mid, idx, val);
        else updateTree(2 * node + 1, mid + 1, end, idx, val);
        tree[node] = tree[2 * node] ^ tree[2 * node + 1];
    }

    private int queryTree(int node, int start, int end, int l, int r) {
        if (r < start || end < l) return 0;
        if (l <= start && end <= r) return tree[node];
        int mid = (start + end) / 2;
        return queryTree(2 * node, start, mid, l, r) ^ queryTree(2 * node + 1, mid + 1, end, l, r);
    }

    private int queryPath(int u, int v) {
        int res = 0;
        while (head[u] != head[v]) {
            if (depth[head[u]] < depth[head[v]]) {
                int temp = u; u = v; v = temp;
            }
            res ^= queryTree(1, 1, n, pos[head[u]], pos[u]);
            u = parent[head[u]];
        }
        if (depth[u] > depth[v]) {
            int temp = u; u = v; v = temp;
        }
        res ^= queryTree(1, 1, n, pos[u], pos[v]);
        return res;
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.n = n;
        this.sChars = s.toCharArray();
        this.timer = 0;
        this.adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        parent = new int[n];
        depth = new int[n];
        heavy = new int[n];
        head = new int[n];
        pos = new int[n];
        sz = new int[n];
        tree = new int[4 * n + 1];

        dfs(0, -1, 0);
        decompose(0, 0);

        for (int i = 0; i < n; i++) {
            int mask = 1 << (sChars[i] - 'a');
            updateTree(1, 1, n, pos[i], mask);
        }

        List<Boolean> ans = new ArrayList<>();
        for (String q : queries) {
            String[] parts = q.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);
                sChars[u] = c;
                int mask = 1 << (c - 'a');
                updateTree(1, 1, n, pos[u], mask);
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int pathXor = queryPath(u, v);
                ans.add((pathXor & (pathXor - 1)) == 0);
            }
        }
        return ans;
    }
}