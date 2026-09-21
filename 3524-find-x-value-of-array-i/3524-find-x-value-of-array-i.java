class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        long[] dp = new long[k]; // dp[r] stores count of subarrays ending at current index with product % k == r

        for (int num : nums) {
            long[] nextDp = new long[k];
            int val = num % k;

            // Start a new subarray with just the current element
            nextDp[val]++;

            // Extend existing subarrays from previous index
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int newRem = (r * val) % k;
                    nextDp[newRem] += dp[r];
                }
            }

            // Update result counts and transition dp state
            for (int r = 0; r < k; r++) {
                result[r] += nextDp[r];
            }
            dp = nextDp;
        }

        return result;
    }
}