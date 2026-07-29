class Solution {

    static final long limit = 1_000_001L;

    public String smallestPalindrome(String s, int k) {

        int[] freq = new int[26];

        for (char c : s.toCharArray())
            freq[c - 'a']++;

        int[] half = new int[26];
        int len = 0;
        char mid = 0;

        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
            len += half[i];

            if ((freq[i] & 1) == 1)
                mid = (char) ('a' + i);
        }

        if (countWays(half, len) < k)
            return "";

        StringBuilder left = new StringBuilder();

        for (int pos = 0; pos < len; pos++) {

            for (int ch = 0; ch < 26; ch++) {

                if (half[ch] == 0)
                    continue;

                half[ch]--;

                long ways = countWays(half, len - pos - 1);

                if (ways >= k) {
                    left.append((char) ('a' + ch));
                    break;
                }

                k -= ways;
                half[ch]++;
            }
        }

        StringBuilder ans = new StringBuilder();

        ans.append(left);

        if (mid != 0)
            ans.append(mid);

        ans.append(new StringBuilder(left).reverse());

        return ans.toString();
    }

    private long countWays(int[] cnt, int total) {

        long res = 1;

        int remaining = total;

        for (int i = 0; i < 26; i++) {

            int c = cnt[i];

            for (int j = 1; j <= c; j++) {

                res = res * (remaining - c + j) / j;

                if (res > limit)
                    return limit;
            }

            remaining -= c;
        }

        return Math.min(res, limit);
    }
}