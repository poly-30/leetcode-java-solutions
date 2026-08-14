class Solution {
    public int maximumLengthSubstring(String s) {
        int[] count = new int[26];
        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            count[rightChar - 'a']++;

            // Shrink window if any character appears more than 2 times
            while (count[rightChar - 'a'] > 2) {
                char leftChar = s.charAt(left);
                count[leftChar - 'a']--;
                left++;
            }

            // Update the max window size
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}