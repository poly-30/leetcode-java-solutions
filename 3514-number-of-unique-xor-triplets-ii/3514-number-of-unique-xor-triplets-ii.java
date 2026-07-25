import java.util.*;

class Solution {
    public int uniqueXorTriplets(int[] nums) {
        // Step 1: Extract unique values to avoid duplicate work
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        
        int[] uniqueNums = new int[set.size()];
        int idx = 0;
        for (int num : set) {
            uniqueNums[idx++] = num;
        }

        // Step 2: Boolean array representing reachable XOR values
        // Maximum value of nums[i] is 1500, so XOR sum won't exceed 2047 (< 2048)
        boolean[] reachable = new boolean[2048];
        reachable[0] = true; // 0 picks

        // Step 3: Expand 3 times (for 3 picks)
        for (int round = 0; round < 3; round++) {
            boolean[] nextReachable = new boolean[2048];
            
            for (int val : uniqueNums) {
                for (int x = 0; x < 2048; x++) {
                    if (reachable[x]) {
                        nextReachable[x ^ val] = true;
                    }
                }
            }
            reachable = nextReachable;
        }

        // Step 4: Count all true values after 3 picks
        int count = 0;
        for (boolean isPossible : reachable) {
            if (isPossible) {
                count++;
            }
        }

        return count;
    }
}