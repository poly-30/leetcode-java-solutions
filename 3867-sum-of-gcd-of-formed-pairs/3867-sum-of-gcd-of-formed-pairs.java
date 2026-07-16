import java.util.Arrays;

class Solution {
    public long gcdSum(int[] nums) {
        int n = nums.length;
        int[] prefixGcd = new int[n];
        int maxEl = -1;
        
        // Step 1: Construct the prefix GCD array using tracking maximum element
        for (int i = 0; i < n; i++) {
            maxEl = Math.max(maxEl, nums[i]);
            prefixGcd[i] = gcd(nums[i], maxEl);
        }
        
        // Step 2: Sort the prefixGcd array in non-decreasing order
        Arrays.sort(prefixGcd);
        
        // Step 3: Two-pointer technique to pair smallest and largest elements
        long result = 0;
        int i = 0;
        int j = n - 1;
        
        while (i < j) {
            result += gcd(prefixGcd[i], prefixGcd[j]);
            i++;
            j--;
        }
        
        return result;
    }
    
    // Helper function to calculate Greatest Common Divisor (GCD)
    private int gcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}
