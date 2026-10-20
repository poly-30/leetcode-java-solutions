class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int[] s = nums.clone();
        java.util.Arrays.sort(s);
        
        int sum = 0;
        for (int k = 1; k * k <= n; k++) {
            if (n % k == 0) {
                if (checkDivisor(nums, s, n, k)) {
                    sum += k;
                }
                int k2 = n / k;
                if (k2 != k) {
                    if (checkDivisor(nums, s, n, k2)) {
                        sum += k2;
                    }
                }
            }
        }
        return sum;
    }
    
    private boolean checkDivisor(int[] nums, int[] s, int n, int k) {
        for (int start = 0; start < n; start += k) {
            if (!isValidBlock(nums, s, start, k)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isValidBlock(int[] nums, int[] s, int start, int k) {
        int dropCount = 0;
        int dropIdx = -1;
        
        // Count circular drops in the current block
        for (int i = 0; i < k; i++) {
            int u = nums[start + i];
            int v = nums[start + (i + 1) % k];
            if (u > v) {
                dropCount++;
                dropIdx = i;
            }
        }
        
        if (dropCount > 1) return false;
        
        // If 0 drops, the block must already match the sorted block
        if (dropCount == 0) {
            for (int i = 0; i < k; i++) {
                if (nums[start + i] != s[start + i]) return false;
            }
            return true;
        }
        
        // If 1 drop, rotating at dropIdx + 1 must match the sorted block
        int rotStart = (dropIdx + 1) % k;
        for (int i = 0; i < k; i++) {
            if (nums[start + (rotStart + i) % k] != s[start + i]) {
                return false;
            }
        }
        return true;
    }
}