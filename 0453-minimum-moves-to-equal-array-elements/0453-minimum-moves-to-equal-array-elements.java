class Solution {
    public int minMoves(int[] nums) {
        int min = nums[0];
        long sum = 0;
        
        // Find the minimum element and sum of all elements
        for (int num : nums) {
            min = Math.min(min, num);
            sum += num;
        }
        
        // Calculate the minimum moves required
        return (int) (sum - (long) nums.length * min);
    }
}