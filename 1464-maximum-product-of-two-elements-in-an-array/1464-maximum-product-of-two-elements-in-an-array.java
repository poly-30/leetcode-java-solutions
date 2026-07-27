class Solution {
    public int maxProduct(int[] nums) {
        int max1 = 0;
        int max2 = 0;
        
        for (int num : nums) {
            if (num > max1) {
                max2 = max1; // Previous max becomes second largest
                max1 = num;  // Update new largest
            } else if (num > max2) {
                max2 = num;  // Update second largest
            }
        }
        
        return (max1 - 1) * (max2 - 1);
    }
}
