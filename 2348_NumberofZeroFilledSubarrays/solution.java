class Solution {
    public long zeroFilledSubarray(int[] nums) {
        long ans = 0;
        int count = 0;
        for (int v : nums) {
            if (v == 0) {
                count++;
            } else {
                count = 0;
            }
            ans += count;
        }
        return ans;
    }
}