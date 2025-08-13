class Solution {
    public boolean isPowerOfThree(int n) {
        if (n <= 0) return false; // negative or zero can't be powers of 3
        while (n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }
}