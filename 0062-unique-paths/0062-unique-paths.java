class Solution {
    public int uniquePaths(int m, int n) {
        long result = 1;
        int totalSteps = m + n - 2;
        int k = Math.min(m - 1, n - 1); // Minimize loop iterations
        
        for (int i = 1; i <= k; i++) {
            result = result * (totalSteps - k + i) / i;
        }
        
        return (int) result;
    }
}