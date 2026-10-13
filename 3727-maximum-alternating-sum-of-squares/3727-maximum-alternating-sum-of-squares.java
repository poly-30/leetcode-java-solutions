import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squares = new long[n];
        
        // Step 1: Compute squares of all elements
        for (int i = 0; i < n; i++) {
            squares[i] = (long) nums[i] * nums[i];
        }
        
        // Step 2: Sort the squares in ascending order
        Arrays.sort(squares);
        
        long totalScore = 0;
        int mid = n / 2;
        
        // Step 3: Subtract the first half (smaller squares)
        for (int i = 0; i < mid; i++) {
            totalScore -= squares[i];
        }
        
        // Step 4: Add the second half (larger squares)
        for (int i = mid; i < n; i++) {
            totalScore += squares[i];
        }
        
        return totalScore;
    }
}