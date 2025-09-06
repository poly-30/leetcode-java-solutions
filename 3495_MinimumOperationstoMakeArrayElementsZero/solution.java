class Solution {
    public long minOperations(int[][] queries) {
        long totalResult = 0;

        for (int[] query : queries) {
            long l = query[0];
            long r = query[1];
            long costForRange = calculatePrefixCost(r) - calculatePrefixCost(l - 1);
            long operationsForQuery = (costForRange + 1) / 2;

            totalResult += operationsForQuery;
        }

        return totalResult;
    }
    private long calculatePrefixCost(long n) {
        if (n == 0) {
            return 0;
        }

        long totalCost = 0;
        long powerOf4 = 1;
        while (powerOf4 <= n) {
            totalCost += (n - powerOf4 + 1);
            if (powerOf4 > Long.MAX_VALUE / 4) {
                break;
            }
            powerOf4 *= 4;
        }

        return totalCost;
    }
}