class Solution {
    /**
     * Finds two integers, A and B, such that A + B = n, and neither A nor B
     * contains the digit 0.
     *
     * @param n The target integer.
     * @return An array of two integers [A, B] that meet the criteria.
     */
    public int[] getNoZeroIntegers(int n) {
        // Iterate through all possible values for the first number, 'a'.
        // We can start from 1 and go up to n-1.
        for (int a = 1; a < n; a++) {
            // The second number, 'b', is determined by 'a'.
            int b = n - a;

            // Check if both 'a' and 'b' are "No-Zero" integers.
            if (!containsZero(a) && !containsZero(b)) {
                // If they are, we have found a valid pair.
                return new int[]{a, b};
            }
        }

        // The problem guarantees a solution exists, so this part is unreachable.
        // It's included to satisfy the compiler's need for a return statement.
        return new int[]{};
    }

    /**
     * A helper method to check if an integer contains the digit 0.
     *
     * @param num The integer to check.
     * @return true if the number contains a 0, false otherwise.
     */
    private boolean containsZero(int num) {
        // We can check each digit using the modulo operator.
        while (num > 0) {
            // If the last digit is 0, the number contains a zero.
            if (num % 10 == 0) {
                return true;
            }
            // Remove the last digit to check the next one.
            num /= 10;
        }
        // If the loop completes, no zero was found.
        return false;
    }
}
