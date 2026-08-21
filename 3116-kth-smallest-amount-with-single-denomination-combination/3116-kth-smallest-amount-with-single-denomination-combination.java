class Solution {
    public long findKthSmallest(int[] coins, int k) {
        long low = 1;
        long high = (long) coins[0] * k;
        for (int c : coins) {
            high = Math.min(high, (long) c * k);
        }

        long ans = high;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (count(coins, mid) >= k) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private long count(int[] coins, long target) {
        int n = coins.length;
        long count = 0;

        // Iterate through all subsets using bitmask (1 to (1 << n) - 1)
        for (int i = 1; i < (1 << n); i++) {
            long currentLcm = 1;
            int setBits = 0;
            boolean overflow = false;

            for (int j = 0; j < n; j++) {
                if (((i >> j) & 1) == 1) {
                    setBits++;
                    currentLcm = lcm(currentLcm, coins[j]);
                    if (currentLcm > target) {
                        overflow = true;
                        break;
                    }
                }
            }

            if (overflow) continue;

            if (setBits % 2 == 1) {
                count += target / currentLcm;
            } else {
                count -= target / currentLcm;
            }
        }
        return count;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}