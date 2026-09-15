class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        int count = 0;
        int lastEnd = -1; // End index of the last selected palindrome

        for (int i = 0; i < n; i++) {
            // Check for valid palindromes starting at center expansion
            // Length k palindrome ending at i
            if (i - k + 1 > lastEnd && isPalindrome(s, i - k + 1, i)) {
                count++;
                lastEnd = i;
            } 
            // Length k + 1 palindrome ending at i
            else if (i - k > lastEnd && isPalindrome(s, i - k, i)) {
                count++;
                lastEnd = i;
            }
        }

        return count;
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}