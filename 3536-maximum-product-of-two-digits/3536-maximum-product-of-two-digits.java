class Solution {
    public int maxProduct(int number) {
        int max1 = 0;
        int max2 = 0;

        while (number > 0) {
            int digit = number % 10;
            
            if (digit > max1) {
                max2 = max1;
                max1 = digit;
            } else if (digit > max2) {
                max2 = digit;
            }
            
            number /= 10;
        }

        return max1 * max2;
    }
}