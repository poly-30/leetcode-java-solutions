class Solution {
    public int minOperations(int[] nums, int x) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        int target = totalSum - x;

        // If target is negative, sum of elements is smaller than x
        if (target < 0) {
            return -1;
        }

        // If target is 0, we need to pick the entire array
        if (target == 0) {
            return nums.length;
        }

        // Sliding window to find the longest subarray with sum equal to target
        int left = 0;
        int currentSum = 0;
        int maxLen = -1;

        for (int right = 0; right < nums.length; right++) {
            currentSum += nums[right];

            // Shrink window if currentSum exceeds target
            while (currentSum > target && left <= right) {
                currentSum -= nums[left];
                left++;
            }

            // Update maximum length if exact target sum is reached
            if (currentSum == target) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }

        return maxLen == -1 ? -1 : nums.length - maxLen;
    }
}