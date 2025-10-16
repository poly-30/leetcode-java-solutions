class Solution {
    public long maximumTotalDamage(int[] power) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int p : power) {
            count.put(p, count.getOrDefault(p, 0) + 1);
        }
        List<Integer> damageList = new ArrayList<>(count.keySet());
        Collections.sort(damageList);
        int n = damageList.size();
        long[][] dp = new long[n][2];
        
        for (int i = 0; i < n; ++i) {
            int damage = damageList.get(i);
            long total = (long) damage * count.get(damage);
            if (i == 0) {
                dp[0][0] = 0;
                dp[0][1] = total;
            } else {
                dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
                dp[i][1] = total;
                if (damageList.get(i-1) != damage - 1 && damageList.get(i-1) != damage - 2) {
                    dp[i][1] += Math.max(dp[i-1][0], dp[i-1][1]);
                } else if (i >= 2 && damageList.get(i-2) != damage - 2) {
                    dp[i][1] += Math.max(dp[i-2][0], dp[i-2][1]);
                } else if (i >= 3) {
                    dp[i][1] += Math.max(dp[i-3][0], dp[i-3][1]);
                }
            }
        }
        return Math.max(dp[n-1][0], dp[n-1][1]);
    }
}
