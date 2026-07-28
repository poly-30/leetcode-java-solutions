class Solution {
    public String smallestPalindrome(String s) {
        int n = s.length();
        int m = n / 2;
        
        // Count frequencies of characters in the first half
        int[] freq = new int[26];
        for (int i = 0; i < m; i++) {
            freq[s.charAt(i) - 'a']++;
        }
        
        // Reconstruct the lexicographically smallest first half
        StringBuilder left = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            while (freq[i] > 0) {
                left.append((char) ('a' + i));
                freq[i]--;
            }
        }
        
        // Mirror the first half to form the complete palindrome
        StringBuilder result = new StringBuilder(left);
        if (n % 2 != 0) {
            result.append(s.charAt(m)); // Keep the exact middle character
        }
        result.append(new StringBuilder(left).reverse());
        
        return result.toString();
    }
}