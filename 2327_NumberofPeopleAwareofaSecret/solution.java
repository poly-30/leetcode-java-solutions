class Solution {
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        long MOD = 1_000_000_007;
        long[] dp = new long[n + 1];
        dp[1] = 1;
        long sharingCount = 0;
        for (int i = 2; i <= n; i++) {
            if (i - delay > 0) {
                sharingCount = (sharingCount + dp[i - delay]) % MOD;
            }
            if (i - forget > 0) {
                sharingCount = (sharingCount - dp[i - forget] + MOD) % MOD;
            }
            dp[i] = sharingCount;
        }
        long totalAware = 0;
        int startDay = n - forget + 1;
        for (int i = startDay; i <= n; i++) {
            if (i > 0) {
                 totalAware = (totalAware + dp[i]) % MOD;
            }
        }
        
        return (int) totalAware;
    }
}
