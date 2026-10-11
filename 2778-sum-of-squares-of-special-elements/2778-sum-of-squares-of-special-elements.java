class Solution {
    public int sumOfSquares(int[] nums) {
        int n = nums.length;
        int totalSum = 0;
        
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                int val = nums[i - 1];
                totalSum += val * val;
            }
        }
        
        return totalSum;
    }
}