class Solution {
    public int[] smallestSubarrays(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        
        // last[b] stores the index of the most recent number from the right
        // that has the b-th bit set. Integers up to 10^9 need up to 30 bits (0-29).
        int[] last = new int[30];

        // Iterate from right to left
        for (int i = n - 1; i >= 0; i--) {
            // Update the last seen positions for the bits present in nums[i]
            for (int b = 0; b < 30; b++) {
                if (((nums[i] >> b) & 1) == 1) {
                    last[b] = i;
                }
            }

            // The end of the minimum subarray must be the farthest 'last' index
            // to ensure all bits from the suffix OR are included.
            int endIdx = i;
            for (int b = 0; b < 30; b++) {
                endIdx = Math.max(endIdx, last[b]);
            }
            
            // The length of the subarray is the distance from i to the end index.
            ans[i] = endIdx - i + 1;
        }

        return ans;
    }
}