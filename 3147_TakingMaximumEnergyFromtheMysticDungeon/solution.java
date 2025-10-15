class Solution {
    public int maximumEnergy(int[] energy, int k) {
        int n = energy.length;
        int[] dp = energy.clone();
        // Start updating dp from the end toward the beginning minus k
        for (int i = n - 1 - k; i >= 0; --i) {
            dp[i] += dp[i + k];
        }
        // The answer is the highest cumulative sum we can start with
        int max = dp[0];
        for (int val : dp) {
            if (val > max) {
                max = val;
            }
        }
        return max;
    }
}
