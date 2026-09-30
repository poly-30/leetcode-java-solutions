class Solution {
    public long countCommas(long n) {
        long totalCommas = 0;
        long threshold = 1000;

        while (n >= threshold) {
            totalCommas += (n - threshold + 1);
            if (threshold > Long.MAX_VALUE / 1000) {
                break;
            }
            threshold *= 1000;
        }

        return totalCommas;
    }
}