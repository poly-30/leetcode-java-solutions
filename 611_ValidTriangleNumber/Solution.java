class Solution {
    public int triangleNumber(int[] nums) {
        int count = 0;
        int n = nums.length;
        // Sort the array to make it easier to apply the triangle inequality
        Arrays.sort(nums);
        // Loop for the largest side
        for (int k = n - 1; k >= 2; k--) {
            int left = 0, right = k - 1;
            // Use two pointers to find two sides whose sum is greater than nums[k]
            while (left < right) {
                if (nums[left] + nums[right] > nums[k]) {
                    count += right - left;
                    right--;
                } else {
                    left++;
                }
            }
        }
        return count;
    }
}
