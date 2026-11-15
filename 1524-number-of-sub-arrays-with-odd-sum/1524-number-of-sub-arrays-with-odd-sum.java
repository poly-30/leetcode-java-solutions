class Solution {
    public int numOfSubarrays(int[] arr) {
        final int MOD = 1_000_000_007;
        int[] prefixCount = {1, 0};
        int result = 0;
        int prefixSum = 0;
        
        for (int num : arr) {
            prefixSum += num;
            int currentParity = prefixSum & 1;
            result = (result + prefixCount[currentParity ^ 1]) % MOD;
            prefixCount[currentParity]++;
        }
        return result;
    }
}