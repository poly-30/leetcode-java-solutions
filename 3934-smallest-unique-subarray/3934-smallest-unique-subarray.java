import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final long BASE1 = 1315423911L;
    private static final long MOD1 = 1000000007L;
    private static final long BASE2 = 100003L;
    private static final long MOD2 = 1000000009L;

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        
        // Precompute powers and prefix hashes for double hashing
        long[] pow1 = new long[n + 1];
        long[] pow2 = new long[n + 1];
        long[] pref1 = new long[n + 1];
        long[] pref2 = new long[n + 1];
        
        pow1[0] = 1;
        pow2[0] = 1;
        
        for (int i = 0; i < n; i++) {
            pow1[i + 1] = (pow1[i] * BASE1) % MOD1;
            pow2[i + 1] = (pow2[i] * BASE2) % MOD2;
            
            pref1[i + 1] = (pref1[i] * BASE1 + nums[i]) % MOD1;
            pref2[i + 1] = (pref2[i] * BASE2 + nums[i]) % MOD2;
        }
        
        int low = 1, high = n;
        int ans = n;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (hasUniqueSubarray(mid, n, pow1, pow2, pref1, pref2)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        
        return ans;
    }

    private boolean hasUniqueSubarray(int len, int n, long[] pow1, long[] pow2, long[] pref1, long[] pref2) {
        Map<Long, Integer> freq = new HashMap<>(n - len + 1);
        
        for (int i = 0; i <= n - len; i++) {
            long h1 = (pref1[i + len] - (pref1[i] * pow1[len]) % MOD1 + MOD1) % MOD1;
            long h2 = (pref2[i + len] - (pref2[i] * pow2[len]) % MOD2 + MOD2) % MOD2;
            
            // Pack both 32-bit hashes into a single 64-bit Long key
            long combinedHash = (h1 << 32) | h2;
            freq.put(combinedHash, freq.getOrDefault(combinedHash, 0) + 1);
        }
        
        for (int count : freq.values()) {
            if (count == 1) {
                return true;
            }
        }
        
        return false;
    }
}
