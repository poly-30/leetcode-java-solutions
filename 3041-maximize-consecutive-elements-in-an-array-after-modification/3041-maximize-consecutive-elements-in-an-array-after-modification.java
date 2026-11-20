import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSelectedElements(int[] nums) {
        // Sort the array to process elements in non-decreasing order
        Arrays.sort(nums);
        
        // dp maps a value `v` to the length of the longest consecutive sequence ending at `v`
        Map<Integer, Integer> dp = new HashMap<>();
        int maxLen = 0;
        
        for (int num : nums) {
            // Option 1: Increase `num` by 1 to extend a sequence ending at `num`
            int lenWithIncrement = dp.getOrDefault(num, 0) + 1;
            dp.put(num + 1, lenWithIncrement);
            
            // Option 2: Keep `num` as is to extend a sequence ending at `num - 1`
            int lenWithoutIncrement = dp.getOrDefault(num - 1, 0) + 1;
            dp.put(num, lenWithoutIncrement);
            
            // Track the overall maximum sequence length
            maxLen = Math.max(maxLen, Math.max(lenWithIncrement, lenWithoutIncrement));
        }
        
        return maxLen;
    }
}
