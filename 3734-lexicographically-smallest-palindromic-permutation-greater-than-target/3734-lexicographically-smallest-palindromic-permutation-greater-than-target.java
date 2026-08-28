class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char ch : s.toCharArray()) count[ch - 'a']++;

        int oddCnt = 0, oddChar = -1;
        for (int c = 0; c < 26; c++) {
            if (count[c] % 2 != 0) { oddCnt++; oddChar = c; }
        }
        if (n % 2 == 0) {
            if (oddCnt != 0) return "";
        } else {
            if (oddCnt != 1) return "";
        }

        int half = n / 2;
        int[] halfCounts = new int[26];
        for (int c = 0; c < 26; c++) halfCounts[c] = count[c] / 2;

        // prefixCounts[i] = remaining half-multiset counts after matching target[0..i-1]
        int[][] prefixCounts = new int[half + 1][];
        prefixCounts[0] = halfCounts.clone();
        int M = half; // length of prefix of target[0..half-1] matchable by the half-multiset
        for (int i = 0; i < half; i++) {
            int[] cur = prefixCounts[i].clone();
            int tc = target.charAt(i) - 'a';
            if (cur[tc] > 0) {
                cur[tc]--;
                prefixCounts[i + 1] = cur;
            } else {
                M = i;
                break;
            }
        }

        // ---- Case B: h == target[0..half-1] exactly ----
        if (M == half) {
            String h = target.substring(0, half);
            if (n % 2 == 1) {
                char m = (char) ('a' + oddChar);
                char tmid = target.charAt(half);
                if (m > tmid) {
                    return buildPalindrome(h, m, n);
                } else if (m == tmid) {
                    String secondHalfCandidate = new StringBuilder(h).reverse().toString();
                    String targetSecondHalf = target.substring(half + 1, n);
                    if (secondHalfCandidate.compareTo(targetSecondHalf) > 0) {
                        return buildPalindrome(h, m, n);
                    }
                }
                // else fall through to Case A
            } else {
                String secondHalfCandidate = new StringBuilder(h).reverse().toString();
                String targetSecondHalf = target.substring(half, n);
                if (secondHalfCandidate.compareTo(targetSecondHalf) > 0) {
                    return buildPalindrome(h, '\0', n);
                }
                // else fall through to Case A
            }
        }

        // ---- Case A: diverge from target as late as possible ----
        int startP = Math.min(M, half - 1);
        for (int p = startP; p >= 0; p--) {
            int[] cur = prefixCounts[p];
            if (cur == null) continue;
            int tc = target.charAt(p) - 'a';
            int chosen = -1;
            for (int c = tc + 1; c < 26; c++) {
                if (cur[c] > 0) { chosen = c; break; }
            }
            if (chosen == -1) continue;

            int[] temp = cur.clone();
            temp[chosen]--;

            StringBuilder sb = new StringBuilder();
            sb.append(target, 0, p);
            sb.append((char) ('a' + chosen));
            for (int c = 0; c < 26; c++) {
                for (int t = 0; t < temp[c]; t++) sb.append((char) ('a' + c));
            }
            String h = sb.toString();
            char m = (n % 2 == 1) ? (char) ('a' + oddChar) : '\0';
            return buildPalindrome(h, m, n);
        }

        return "";
    }

    private String buildPalindrome(String h, char mid, int n) {
        StringBuilder sb = new StringBuilder();
        sb.append(h);
        if (n % 2 == 1) sb.append(mid);
        sb.append(new StringBuilder(h).reverse());
        return sb.toString();
    }
}