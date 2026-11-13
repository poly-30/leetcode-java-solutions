import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, long k) {
        int n = nums.length;
        long ans = 0;
        long cost = 0;
        
        // Deque stores indices with nums[i] in non-increasing order from front to back
        Deque<Integer> dq = new ArrayDeque<>();
        int j = n - 1;
        
        for (int i = n - 1; i >= 0; i--) {
            int num = nums[i];
            
            // Maintain monotonic decreasing order in deque
            while (!dq.isEmpty() && nums[dq.peekLast()] < num) {
                int l = dq.pollLast();
                int r = dq.isEmpty() ? j + 1 : dq.peekLast();
                cost += (long) (r - l) * (num - nums[l]);
            }
            dq.addLast(i);
            
            // Shrink the window from the right (j) if cost exceeds k
            while (cost > k) {
                int frontIdx = dq.peekFirst();
                cost -= nums[frontIdx] - nums[j];
                if (frontIdx == j) {
                    dq.pollFirst();
                }
                j--;
            }
            
            // All valid subarrays starting at index i up to j
            ans += (j - i + 1);
        }
        
        return ans;
    }
}