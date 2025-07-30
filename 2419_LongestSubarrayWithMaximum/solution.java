import java.util.*;

class Solution {
    public int longestSubarray(int[] nums) {
        // Step 1: Find the maximum value in the array.
        // Constraints state nums.length >= 1, so the array is not empty.
        int maxVal = 0;
        for (int num : nums) {
            if (num > maxVal) {
                maxVal = num;
            }
        }

        // Step 2: Find the length of the longest contiguous run of maxVal.
        int maxLength = 0;
        int currentLength = 0;
        for (int num : nums) {
            if (num == maxVal) {
                // If the current number is the max value, extend the current streak.
                currentLength++;
            } else {
                // If the streak is broken, update the max length found so far.
                maxLength = Math.max(maxLength, currentLength);
                // Reset the current streak length.
                currentLength = 0;
            }
        }

        // Final check to account for a streak at the end of the array.
        return Math.max(maxLength, currentLength);
    }
}