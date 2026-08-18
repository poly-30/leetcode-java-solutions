import java.util.*;

class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        
        // Case 1: k == 1 -> Largest element with frequency 1 in nums
        if (k == 1) {
            Map<Integer, Integer> freq = new HashMap<>();
            for (int num : nums) {
                freq.put(num, freq.getOrDefault(num, 0) + 1);
            }
            int ans = -1;
            for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
                if (entry.getValue() == 1) {
                    ans = Math.max(ans, entry.getKey());
                }
            }
            return ans;
        }
        
        // Case 2: k == n -> Largest element in the entire array
        if (k == n) {
            int max = -1;
            for (int num : nums) {
                max = Math.max(max, num);
            }
            return max;
        }
        
        // Case 3: 1 < k < n -> Count frequencies of first and last elements
        int first = nums[0];
        int last = nums[n - 1];
        
        int countFirst = 0;
        int countLast = 0;
        
        for (int num : nums) {
            if (num == first) countFirst++;
            if (num == last) countLast++;
        }
        
        int ans = -1;
        if (countFirst == 1) ans = Math.max(ans, first);
        if (countLast == 1) ans = Math.max(ans, last);
        
        return ans;
    }
}