class Solution {
    public int longestSubsequence(int[] nums) {
        int totalXor = 0;
        boolean hasNonZero = false;
        
        // Calculate the total XOR sum and check for any non-zero element
        for (int num : nums) {
            totalXor ^= num;
            if (num != 0) {
                hasNonZero = true;
            }
        }
        
        // Case 1: All elements are zero
        if (!hasNonZero) {
            return 0;
        }
        
        // Case 2: The entire array yields a non-zero XOR sum
        if (totalXor != 0) {
            return nums.length;
        }
        
        // Case 3: Total XOR sum is zero, remove one non-zero element
        return nums.length - 1;
    }
}
