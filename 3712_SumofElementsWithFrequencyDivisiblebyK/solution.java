import java.util.*;

class Solution {
    public int sumDivisibleByK(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        // Count frequencies
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        int sum = 0;
        // Sum all occurrences of elements whose frequency is divisible by k
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() % k == 0) {
                sum += entry.getKey() * entry.getValue();
            }
        }
        return sum;
    }
}
