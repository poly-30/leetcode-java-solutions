class Solution {
    // Euclidean algorithm to find the greatest common divisor
    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
    
    public int gcdOfOddEvenSums(int n) {
        // The sum of the first n odd numbers is always n^2
        int sumOdd = n * n;
        
        // The sum of the first n even numbers is always n * (n + 1)
        int sumEven = n * (n + 1);
        
        return gcd(sumOdd, sumEven);
    }
}