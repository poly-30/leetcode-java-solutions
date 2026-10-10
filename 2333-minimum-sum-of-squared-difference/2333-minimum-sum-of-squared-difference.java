class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] counts = new int[100001];
        long totalDiff = 0;
        int maxDiff = 0;
        
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            if (diff > 0) {
                counts[diff]++;
                maxDiff = Math.max(maxDiff, diff);
                totalDiff += diff;
            }
        }
        
        long k = (long) k1 + k2;
        if (totalDiff <= k) {
            return 0;
        }
        
        for (int i = maxDiff; i > 0 && k > 0; i--) {
            if (counts[i] > 0) {
                long reduce = Math.min((long) counts[i], k);
                counts[i] -= reduce;
                counts[i - 1] += reduce;
                k -= reduce;
            }
        }
        
        long result = 0;
        for (long i = 1; i <= maxDiff; i++) {
            if (counts[(int)i] > 0) {
                result += counts[(int)i] * i * i;
            }
        }
        
        return result;
    }
}