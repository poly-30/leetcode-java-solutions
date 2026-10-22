class Solution {

    private int solve(int[] nums, int start, int end, int player, int[][][] dp) {
        if (start > end) {
            return 0;
        }

        if (dp[start][end][player] != -1) {
            return dp[start][end][player];
        }

        if (player == 1) {
            int choice1 = nums[start] + solve(nums, start + 1, end, 0, dp);
            int choice2 = nums[end] + solve(nums, start, end - 1, 0, dp);
            return dp[start][end][player] = Math.max(choice1, choice2);
        } else {
            int choice1 = -nums[start] + solve(nums, start + 1, end, 1, dp);
            int choice2 = -nums[end] + solve(nums, start, end - 1, 1, dp);
            return dp[start][end][player] = Math.min(choice1, choice2);
        }
    }

    public boolean predictTheWinner(int[] nums) {
        int[][][] dp = new int[nums.length][nums.length][2];
        for (int[][] dp1 : dp) {
            for (int[] dp2 : dp1) {
                Arrays.fill(dp2, -1);
            }
        }
        return solve(nums, 0, nums.length - 1, 1, dp) >= 0 ? true : false;
    }
}