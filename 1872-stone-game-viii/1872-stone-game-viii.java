class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        
        // Step 1: Compute prefix sums in-place
        int[] pref = new int[n];
        pref[0] = stones[0];
        for (int i = 1; i < n; i++) {
            pref[i] = pref[i - 1] + stones[i];
        }
        
        // Step 2: Dynamic programming from right to left
        // Base case: picking all stones up to index n - 1
        int dp = pref[n - 1];
        
        // Iterate backwards from index n - 2 down to index 1 (since x > 1)
        for (int i = n - 2; i > 0; i--) {
            dp = Math.max(dp, pref[i] - dp);
        }
        
        return dp;
    }
}