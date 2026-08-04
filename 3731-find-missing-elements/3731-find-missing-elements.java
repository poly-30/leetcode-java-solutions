import java.util.*;

class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        // Track present elements using a boolean array (values are <= 100)
        boolean[] present = new boolean[101];

        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
            present[num] = true;
        }

        List<Integer> missing = new ArrayList<>();
        
        // Collect missing numbers in sorted order
        for (int i = min + 1; i < max; i++) {
            if (!present[i]) {
                missing.add(i);
            }
        }

        return missing;
    }
}