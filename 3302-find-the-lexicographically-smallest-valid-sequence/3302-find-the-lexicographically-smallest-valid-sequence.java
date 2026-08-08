class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int[] last = new int[m + 1];
        last[m] = n;

        int p = n - 1;
        for (int i = m - 1; i >= 0; i--) {
            while (p >= 0 && word1.charAt(p) != word2.charAt(i)) {
                p--;
            }
            last[i] = p;
            if (p >= 0) p--;
        }

        int[] result = new int[m];
        boolean changed = false;
        int w1Idx = 0;

        for (int w2Idx = 0; w2Idx < m; w2Idx++) {
            boolean matched = false;
            while (w1Idx < n) {
                if (word1.charAt(w1Idx) == word2.charAt(w2Idx)) {
                    result[w2Idx] = w1Idx;
                    w1Idx++;
                    matched = true;
                    break;
                } else if (!changed && last[w2Idx + 1] > w1Idx) {
                    result[w2Idx] = w1Idx;
                    changed = true;
                    w1Idx++;
                    matched = true;
                    break;
                }
                w1Idx++;
            }
            if (!matched) return new int[0];
        }

        return result;
    }
}