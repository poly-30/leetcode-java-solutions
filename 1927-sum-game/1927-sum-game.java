class Solution {
    public boolean sumGame(String num) {
        int n = num.length() / 2;
        int sLeft = 0, qLeft = 0;
        int sRight = 0, qRight = 0;

        // Calculate sum and question mark count for left half
        for (int i = 0; i < n; i++) {
            char c = num.charAt(i);
            if (c == '?') {
                qLeft++;
            } else {
                sLeft += c - '0';
            }
        }

        // Calculate sum and question mark count for right half
        for (int i = n; i < num.length(); i++) {
            char c = num.charAt(i);
            if (c == '?') {
                qRight++;
            } else {
                sRight += c - '0';
            }
        }

        int sDiff = sLeft - sRight;
        int qDiff = qLeft - qRight;

        // Bob wins if and only if sDiff + 4.5 * qDiff == 0
        return (sDiff * 2 + qDiff * 9) != 0;
    }
}