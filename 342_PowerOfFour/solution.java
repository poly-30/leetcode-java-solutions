class Solution {
    public boolean isPowerOfFour(int n) {
        if (n <= 0) return false; // negative or zero can't be powers of 4
        while (n % 4 == 0) {
            n /= 4;
        }
        return n == 1;
    }
}