class Solution {
    public int triangularSum(int[] nums) {
        for (int sz = nums.length; sz > 1; --sz) {
            for (int i = 0; i < sz - 1; ++i) {
                nums[i] = (nums[i] + nums[i + 1]) % 10;
            }
        }
        return nums[0];
    }
}
