class Solution {
    /**
     * Calculates the sum of the frequencies of the most frequent vowel and the
     * most frequent consonant in a string.
     *
     * @param s The input string, consisting of lowercase English letters.
     * @return The sum of the maximum frequencies.
     */
    public int maxFreqSum(String s) {
        // Arrays to store frequencies of each character.
        // Index 0 is 'a', 1 is 'b', and so on.
        int[] vowelFreq = new int[26];
        int[] consonantFreq = new int[26];

        // Variables to keep track of the highest frequency found for each type.
        int maxVowelFreq = 0;
        int maxConsonantFreq = 0;

        // Iterate through the string once to count frequencies.
        for (char c : s.toCharArray()) {
            int index = c - 'a'; // Calculate the index for the character (0-25).

            if (isVowel(c)) {
                vowelFreq[index]++;
                // Update the max vowel frequency if the current one is higher.
                maxVowelFreq = Math.max(maxVowelFreq, vowelFreq[index]);
            } else { // The character is a consonant.
                consonantFreq[index]++;
                // Update the max consonant frequency if the current one is higher.
                maxConsonantFreq = Math.max(maxConsonantFreq, consonantFreq[index]);
            }
        }

        // The result is the sum of the two maximums.
        return maxVowelFreq + maxConsonantFreq;
    }

    /**
     * A helper function to check if a character is a vowel.
     *
     * @param c The character to check.
     * @return true if the character is a vowel, false otherwise.
     */
    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}