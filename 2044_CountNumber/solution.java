class Solution {
    public int countMaxOrSubsets(int[] nums) {
        int maxOr = 0;
        for (int num : nums) {
            maxOr |= num;
        }
        return countRecursive(nums, 0, 0, maxOr);
    }
    private int countRecursive(int[] nums, int index, int currentOr, int maxOr) {
        if (index == nums.length) {
            return currentOr == maxOr ? 1 : 0;
        }
        int countWithout = countRecursive(nums, index + 1, currentOr, maxOr);
        int countWith = countRecursive(nums, index + 1, currentOr | nums[index], maxOr);
        return countWith + countWithout;
    }
}