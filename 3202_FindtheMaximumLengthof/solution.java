class Solution {
    public int maximumLength(int[] nums, int k) {
        int[][] dp = new int[k][k];
        int res = 0;
        for(int num : nums){
            int currentMod = num % k;
            for(int preMod = 0; preMod < k; ++preMod){
                dp[preMod][currentMod] = dp[currentMod][preMod] + 1;
                res = Math.max(res, dp[preMod][currentMod]);
            }
        }
        return res;
    }
}