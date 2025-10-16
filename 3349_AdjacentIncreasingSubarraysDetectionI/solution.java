class Solution {
    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        int n = nums.size();
        // Array to store length of increasing subarray ending at index i
        int[] inc = new int[n];
        inc[0] = 1;
        for (int i = 1; i < n; ++i) {
            if (nums.get(i) > nums.get(i - 1)) {
                inc[i] = inc[i - 1] + 1;
            } else {
                inc[i] = 1;
            }
        }
        // Now check for two adjacent subarrays of length k that are strictly increasing
        for (int i = k * 2 - 1; i < n; ++i) {
            if (inc[i] >= k && inc[i - k] >= k) {
                return true;
            }
        }
        return false;
    }
}
