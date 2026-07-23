class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        
        // Base cases for small n
        if (n == 1) return 1;
        if (n == 2) return 2;
        
        // For n >= 3, the answer is the smallest power of 2 strictly greater than n
        return Integer.highestOneBit(n) << 1;
    }
}