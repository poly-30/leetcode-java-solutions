class Solution {
    public double new21Game(int n, int k, int maxPts) {
        // If k == 0 or n >= k - 1 + maxPts, the probability is 1.
        if (k == 0 || n >= k - 1 + maxPts) 
            return 1.0;
        
        double ans = 0.0;
        double[] dp = new double[n + 1]; // dp[i]: probability of getting i points
        dp[0] = 1.0;
        double windowSum = 1.0; // sum of probabilities for the sliding window

        for (int i = 1; i <= n; ++i) {
            dp[i] = windowSum / maxPts;
            if (i < k)
                windowSum += dp[i];
            else
                ans += dp[i];
            if (i - maxPts >= 0)
                windowSum -= dp[i - maxPts];
        }

        return ans;
    }
}