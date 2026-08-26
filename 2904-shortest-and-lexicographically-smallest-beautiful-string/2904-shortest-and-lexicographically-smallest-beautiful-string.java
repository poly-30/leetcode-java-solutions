class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int left = 0;
        int countOnes = 0;
        String result = "";

        for (int right = 0; right < n; right++) {
            if (s.charAt(right) == '1') {
                countOnes++;
            }

            // Shrink the window when we have exactly k '1's
            while (countOnes == k) {
                String current = s.substring(left, right + 1);

                // Update result if it's smaller in length or lexicographically smaller for same length
                if (result.isEmpty() || current.length() < result.length() || 
                   (current.length() == result.length() && current.compareTo(result) < 0)) {
                    result = current;
                }

                // Move left pointer to find shorter or alternative substrings
                if (s.charAt(left) == '1') {
                    countOnes--;
                }
                left++;
            }
        }

        return result;
    }
}