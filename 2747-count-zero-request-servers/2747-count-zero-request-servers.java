import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = queries.length;
        Integer[] id = new Integer[m];
        for (int i = 0; i < m; i++) {
            id[i] = i;
        }

        // Sort queries by their values while preserving original indices
        Arrays.sort(id, (i, j) -> queries[i] - queries[j]);

        // Sort logs by time
        Arrays.sort(logs, Comparator.comparingInt(a -> a[1]));

        int[] ans = new int[m];
        int[] cnt = new int[n + 1];
        int outOfRange = n;
        int left = 0, right = 0;

        for (int i : id) {
            int queryTime = queries[i];
            int windowStart = queryTime - x;

            // Expand window to include logs up to queryTime
            while (right < logs.length && logs[right][1] <= queryTime) {
                int serverId = logs[right][0];
                if (cnt[serverId]++ == 0) {
                    outOfRange--;
                }
                right++;
            }

            // Shrink window to remove logs older than windowStart
            while (left < logs.length && logs[left][1] < windowStart) {
                int serverId = logs[left][0];
                if (--cnt[serverId] == 0) {
                    outOfRange++;
                }
                left++;
            }

            ans[i] = outOfRange;
        }

        return ans;
    }
}
