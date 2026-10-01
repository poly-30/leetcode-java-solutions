class Solution {
    static class Node {
        int prod;
        int[] cnt; // cnt[r] = count of prefixes in this segment with product % k == r

        Node(int k) {
            this.prod = 1;
            this.cnt = new int[k];
        }
    }

    private Node[] tree;
    private int[] nums;
    private int n, k;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.nums = nums;
        this.n = nums.length;
        this.k = k;
        this.tree = new Node[4 * n];

        build(1, 0, n - 1);

        int[] result = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {
            int idx = queries[q][0];
            int val = queries[q][1];
            int start = queries[q][2];
            int targetX = queries[q][3];

            // 1. Point update
            update(1, 0, n - 1, idx, val);

            // 2. Query range [start, n - 1]
            Node queryResult = query(1, 0, n - 1, start, n - 1);

            result[q] = queryResult.cnt[targetX];
        }

        return result;
    }

    private Node merge(Node left, Node right) {
        if (left == null) return right;
        if (right == null) return left;

        Node parent = new Node(k);
        parent.prod = (left.prod * right.prod) % k;

        // Left child prefix counts
        for (int r = 0; r < k; r++) {
            parent.cnt[r] += left.cnt[r];
        }

        // Right child prefix counts transformed by left child's total product
        for (int r = 0; r < k; r++) {
            int newRem = (left.prod * r) % k;
            parent.cnt[newRem] += right.cnt[r];
        }

        return parent;
    }

    private void build(int node, int start, int end) {
        tree[node] = new Node(k);
        if (start == end) {
            int valMod = nums[start] % k;
            tree[node].prod = valMod;
            tree[node].cnt[valMod] = 1;
            return;
        }

        int mid = start + (end - start) / 2;
        build(2 * node, start, mid);
        build(2 * node + 1, mid + 1, end);

        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            nums[idx] = val;
            int valMod = val % k;
            tree[node].prod = valMod;
            java.util.Arrays.fill(tree[node].cnt, 0);
            tree[node].cnt[valMod] = 1;
            return;
        }

        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx, val);
        } else {
            update(2 * node + 1, mid + 1, end, idx, val);
        }

        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private Node query(int node, int start, int end, int l, int r) {
        if (r < start || end < l) {
            return null;
        }

        if (l <= start && end <= r) {
            return tree[node];
        }

        int mid = start + (end - start) / 2;
        Node leftResult = query(2 * node, start, mid, l, r);
        Node rightResult = query(2 * node + 1, mid + 1, end, l, r);

        return merge(leftResult, rightResult);
    }
}