import java.util.HashSet;
import java.util.Set;
import java.util.Collections;

class Solution {
    public int maxSum(int[] nums) {
        Set<Integer> uniqueNums = new HashSet<>();
        for (int num : nums) {
            uniqueNums.add(num);
        }
        int positiveSum = 0;
        for (int num : uniqueNums) {
            if (num > 0) {
                positiveSum += num;
            }
        }
        if (positiveSum > 0) {
            return positiveSum;
        } else {
            return Collections.max(uniqueNums);
        }
    }
}