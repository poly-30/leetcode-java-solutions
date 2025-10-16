class Solution {
    public int longestBalanced(String s) {
        int maxLen = 0;
        int n = s.length();

        for (int start = 0; start < n; start++) {
            int[] freq = new int[26];  // frequency for a-z
            int distinctCount = 0;

            for (int end = start; end < n; end++) {
                int idx = s.charAt(end) - 'a';
                freq[idx]++;
                if (freq[idx] == 1) distinctCount++;

                // Early check: if frequencies differ too much, break
                if (!canBeBalanced(freq, distinctCount)) {
                    break;
                }

                if (isBalanced(freq, distinctCount)) {
                    maxLen = Math.max(maxLen, end - start + 1);
                }
            }
        }
        return maxLen;
    }

    private boolean canBeBalanced(int[] freq, int distinctCount) {
        // Check if max freq - min freq among non-zero frequencies <= 1
        int minFreq = Integer.MAX_VALUE;
        int maxFreq = Integer.MIN_VALUE;
        for (int f : freq) {
            if (f > 0) {
                minFreq = Math.min(minFreq, f);
                maxFreq = Math.max(maxFreq, f);
                if (maxFreq - minFreq > 1) return false;
            }
        }
        return true;
    }

    private boolean isBalanced(int[] freq, int distinctCount) {
        // Check all non-zero frequencies are equal
        int count = -1;
        for (int f : freq) {
            if (f > 0) {
                if (count == -1) count = f;
                else if (count != f) return false;
            }
        }
        return true;
    }
}
