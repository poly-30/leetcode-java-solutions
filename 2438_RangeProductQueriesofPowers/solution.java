import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] productQueries(int n, int[][] queries) {
        final int MOD = 1_000_000_007;
        List<Integer> exponents = new ArrayList<>();
        for (int i = 0; i < 31; i++) { 
            if (((n >> i) & 1) == 1) {
                exponents.add(i);
            }
        }
        int numPowers = exponents.size();
        long[] prefixSum = new long[numPowers + 1];
        for (int i = 0; i < numPowers; i++) {
            prefixSum[i + 1] = prefixSum[i] + exponents.get(i);
        }
        int numQueries = queries.length;
        int[] answers = new int[numQueries];
        for (int i = 0; i < numQueries; i++) {
            int left = queries[i][0];
            int right = queries[i][1];

            long totalExponent = prefixSum[right + 1] - prefixSum[left];
            answers[i] = (int) power(2, totalExponent, MOD);
        }

        return answers;
    }
    private long power(long base, long exp, int mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {

            if (exp % 2 == 1) {
                res = (res * base) % mod;
            }
            base = (base * base) % mod;
            exp /= 2;
        }
        return res;
    }
}