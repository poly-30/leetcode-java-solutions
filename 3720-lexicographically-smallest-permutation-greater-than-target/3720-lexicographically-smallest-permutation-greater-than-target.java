import java.util.Arrays;

class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // Stores available count of characters remaining at each step prefixing target
        int[][] countsAt = new int[n + 1][26];
        countsAt[0] = count.clone();

        // Step 1: Try matching target character-by-character from left to right
        int matchedLength = 0;
        for (int i = 0; i < n; i++) {
            int charIdx = target.charAt(i) - 'a';
            if (countsAt[i][charIdx] > 0) {
                countsAt[i + 1] = countsAt[i].clone();
                countsAt[i + 1][charIdx]--;
                matchedLength++;
            } else {
                break;
            }
        }

        // Step 2: Backtrack from the longest possible prefix down to 0
        for (int i = matchedLength; i >= 0; i--) {
            if (i < n) {
                int targetCharIdx = target.charAt(i) - 'a';
                // Find smallest character strictly greater than target[i]
                for (int c = targetCharIdx + 1; c < 26; c++) {
                    if (countsAt[i][c] > 0) {
                        // Construct answer
                        StringBuilder result = new StringBuilder();
                        result.append(target.substring(0, i));
                        result.append((char) ('a' + c));

                        int[] remCount = countsAt[i].clone();
                        remCount[c]--;

                        // Append remaining characters in ascending order
                        for (int ch = 0; ch < 26; ch++) {
                            while (remCount[ch] > 0) {
                                result.append((char) ('a' + ch));
                                remCount[ch]--;
                            }
                        }
                        return result.toString();
                    }
                }
            }
        }

        return "";
    }
}