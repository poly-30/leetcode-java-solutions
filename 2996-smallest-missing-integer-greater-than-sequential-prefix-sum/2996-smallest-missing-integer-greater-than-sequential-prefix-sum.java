import java.util.HashSet;
import java.util.Set;

class Solution {
    public int missingInteger(int[] nums) {
        // Step 1: Find the sum of the longest sequential prefix
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                sum += nums[i];
            } else {
                break;
            }
        }
        
        // Step 2: Store elements in a HashSet for O(1) lookup
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        
        // Step 3: Find the smallest integer >= sum missing from nums
        while (set.contains(sum)) {
            sum++;
        }
        
        return sum;
    }
}