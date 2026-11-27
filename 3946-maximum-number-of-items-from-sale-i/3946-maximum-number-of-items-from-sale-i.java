class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int[] dp = new int[budget + 1];
        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];
            minPrice = Math.min(minPrice, price);

            int gain = 0;
            for (int[] other : items) {
                if (other[0] % factor == 0) {
                    gain++;
                }
            }

            for (int j = budget; j >= price; j--) {
                dp[j] = Math.max(dp[j], dp[j - price] + gain);
            }
        }

        int maxItems = 0;
        for (int spent = 0; spent <= budget; spent++) {
            int extraCopies = (budget - spent) / minPrice;
            maxItems = Math.max(maxItems, dp[spent] + extraCopies);
        }

        return maxItems;
    }
}
