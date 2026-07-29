import java.util.HashMap;
import java.util.Map;

class Solution {
    public int subarraySum(int[] nums, int k) {
        // Map to store (prefix_sum -> frequency)
        Map<Integer, Integer> map = new HashMap<>();
        
        // Base case: prefix sum of 0 occurs once (for subarrays starting at index 0)
        map.put(0, 1);
        
        int currentSum = 0;
        int count = 0;
        
        for (int num : nums) {
            currentSum += num;
            
            // Check if there exists a prefix sum such that: currentSum - prefixSum = k
            if (map.containsKey(currentSum - k)) {
                count += map.get(currentSum - k);
            }
            
            // Record / update the count of the current prefix sum
            map.put(currentSum, map.getOrDefault(currentSum, 0) + 1);
        }
        
        return count;
    }
}