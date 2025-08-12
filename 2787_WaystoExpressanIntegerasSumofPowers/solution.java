public class Solution {
    public int numberOfWays(int n, int x) {
        final int MOD = (int)1e9 + 7;
        int[][] dp = new int[n + 1][n + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            int power = (int) Math.pow(i, x);
            for (int j = 0; j <= n; j++) {
                dp[i][j] = dp[i-1][j];
                if (power <= j) {
                    dp[i][j] = (dp[i][j] + dp[i-1][j - power]) % MOD;
                }
            }
        }
        return dp[n][n];
    }
}
