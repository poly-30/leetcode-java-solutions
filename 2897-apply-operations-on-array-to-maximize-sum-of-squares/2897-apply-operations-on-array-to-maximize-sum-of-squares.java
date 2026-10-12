class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] bitCounts = new int[30];
        for (int x : nums) {
            for (int i = 0; i < 30; i++) {
                if ((x & (1 << i)) != 0) {
                    bitCounts[i]++;
                }
            }
        }
        
        long ans = 0;
        long mod = 1000000007;
        
        for (int step = 0; step < k; step++) {
            long curr = 0;
            for (int i = 0; i < 30; i++) {
                if (bitCounts[i] > 0) {
                    curr |= (1 << i);
                    bitCounts[i]--;
                }
            }
            ans = (ans + curr * curr) % mod;
        }
        
        return (int) ans;
    }
}