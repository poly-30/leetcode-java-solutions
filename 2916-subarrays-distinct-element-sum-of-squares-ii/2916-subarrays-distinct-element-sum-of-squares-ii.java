class Solution {
    static final int MOD = 1000000007;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        long totalSum = 0;
        
        long[] sumTree = new long[4 * n];
        long[] sqTree = new long[4 * n];
        long[] lazy = new long[4 * n];
        
        int[] last = new int[100005];
        java.util.Arrays.fill(last, -1);
        
        for (int i = 0; i < n; i++) {
            int prev = last[nums[i]];
            update(1, 0, n - 1, prev + 1, i, 1, sumTree, sqTree, lazy);
            last[nums[i]] = i;
            
            long sqVal = querySq(1, 0, n - 1, 0, i, sumTree, sqTree, lazy);
            totalSum = (totalSum + sqVal) % MOD;
        }
        
        return (int) totalSum;
    }
    
    private void pushDown(int node, int l, int r, long[] sumTree, long[] sqTree, long[] lazy) {
        if (lazy[node] != 0) {
            int mid = (l + r) / 2;
            apply(2 * node, l, mid, lazy[node], sumTree, sqTree, lazy);
            apply(2 * node + 1, mid + 1, r, lazy[node], sumTree, sqTree, lazy);
            lazy[node] = 0;
        }
    }
    
    private void apply(int node, int l, int r, long val, long[] sumTree, long[] sqTree, long[] lazy) {
        long len = r - l + 1;
        long currentSum = sumTree[node];
        long addedSq = (2 * val % MOD * currentSum % MOD + val * val % MOD * len) % MOD;
        sqTree[node] = (sqTree[node] + addedSq) % MOD;
        sumTree[node] = (sumTree[node] + val * len) % MOD;
        lazy[node] = (lazy[node] + val) % MOD;
    }
    
    private void update(int node, int l, int r, int ql, int qr, long val, long[] sumTree, long[] sqTree, long[] lazy) {
        if (ql > r || qr < l) return;
        if (ql <= l && r <= qr) {
            apply(node, l, r, val, sumTree, sqTree, lazy);
            return;
        }
        pushDown(node, l, r, sumTree, sqTree, lazy);
        int mid = (l + r) / 2;
        update(2 * node, l, mid, ql, qr, val, sumTree, sqTree, lazy);
        update(2 * node + 1, mid + 1, r, ql, qr, val, sumTree, sqTree, lazy);
        sumTree[node] = (sumTree[2 * node] + sumTree[2 * node + 1]) % MOD;
        sqTree[node] = (sqTree[2 * node] + sqTree[2 * node + 1]) % MOD;
    }
    
    private long querySq(int node, int l, int r, int ql, int qr, long[] sumTree, long[] sqTree, long[] lazy) {
        if (ql > r || qr < l) return 0;
        if (ql <= l && r <= qr) return sqTree[node];
        pushDown(node, l, r, sumTree, sqTree, lazy);
        int mid = (l + r) / 2;
        long left = querySq(2 * node, l, mid, ql, qr, sumTree, sqTree, lazy);
        long right = querySq(2 * node + 1, mid + 1, r, ql, qr, sumTree, sqTree, lazy);
        return (left + right) % MOD;
    }
}