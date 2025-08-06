import java.util.*;

class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int n = fruits.length;
        // Baskets: [capacity, original index]
        int[][] sortedBaskets = new int[n][2];
        for (int i = 0; i < n; i++) {
            sortedBaskets[i][0] = baskets[i];
            sortedBaskets[i][1] = i;
        }
        Arrays.sort(sortedBaskets, (a, b) -> Integer.compare(a[0], b[0]));

        // Map from sorted order to original index
        int[] basketIndexes = new int[n];
        for (int i = 0; i < n; i++) {
            basketIndexes[i] = sortedBaskets[i][1];
        }

        // Build segment tree to track smallest available index
        SegmentTree segTree = new SegmentTree(basketIndexes);

        boolean[] used = new boolean[n]; // For debugging, not necessary

        int unplaced = 0;

        for (int fi = 0; fi < n; fi++) {
            int fruit = fruits[fi];

            // Binary search: find first basket with capacity >= fruit
            int l = 0, r = n - 1, idx = n;
            while (l <= r) {
                int m = l + (r - l) / 2;
                if (sortedBaskets[m][0] >= fruit) {
                    idx = m;
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }

            if (idx == n) {
                // No basket with enough capacity
                unplaced++;
                continue;
            }

            // Query segment tree for minimum original index in [idx, n - 1]
            int minOri = segTree.query(idx, n - 1);
            if (minOri == Integer.MAX_VALUE) {
                // No available basket in this range
                unplaced++;
            } else {
                // Find which sortedBaskets position has this index
                // To mark it as used in segment tree
                int pos = Arrays.binarySearch(basketIndexes, minOri);
                if (pos < 0) {
                    // In practice, won't happen
                    pos = idx;
                    while (pos < n && basketIndexes[pos] != minOri) pos++;
                    if (pos == n) throw new RuntimeException();
                }
                segTree.update(pos, Integer.MAX_VALUE);
            }
        }
        return unplaced;
    }

    // Segment tree for range minimum query and point update
    static class SegmentTree {
        int[] tree;
        int n;

        public SegmentTree(int[] data) {
            n = data.length;
            tree = new int[n * 4];
            build(1, 0, n - 1, data);
        }
        void build(int node, int l, int r, int[] data) {
            if (l == r) {
                tree[node] = data[l];
            } else {
                int m = (l + r) / 2;
                build(node * 2, l, m, data);
                build(node * 2 + 1, m + 1, r, data);
                tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
            }
        }
        // Point update: set tree[pos] = val
        void update(int pos, int val) {
            update(1, 0, n - 1, pos, val);
        }
        void update(int node, int l, int r, int pos, int val) {
            if (l == r) {
                tree[node] = val;
            } else {
                int m = (l + r) / 2;
                if (pos <= m) update(node * 2, l, m, pos, val);
                else update(node * 2 + 1, m + 1, r, pos, val);
                tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
            }
        }
        // Range min query [ql, qr] inclusive
        int query(int ql, int qr) {
            return query(1, 0, n - 1, ql, qr);
        }
        int query(int node, int l, int r, int ql, int qr) {
            if (ql > r || qr < l) return Integer.MAX_VALUE;
            if (ql <= l && r <= qr) return tree[node];
            int m = (l + r) / 2;
            return Math.min(query(node * 2, l, m, ql, qr),
                            query(node * 2 + 1, m + 1, r, ql, qr));
        }
    }
}