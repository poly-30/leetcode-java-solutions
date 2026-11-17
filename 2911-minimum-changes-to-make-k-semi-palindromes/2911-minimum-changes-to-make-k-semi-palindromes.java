import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        
        // 1. Precompute proper divisors for all possible substring lengths
        List<Integer>[] divisors = getDivisors(n);
        
        // 2. Precompute minimum changes needed to make substring s[i..j] a semi-palindrome
        int[][] cost = precomputeCosts(s, n, divisors);
        
        // 3. DP: dp[i][j] = min changes to partition suffix s[i..n-1] into j semi-palindromes
        int[][] dp = new int[n + 1][k + 1];
        for (int[] row : dp) {
            Arrays.fill(row, n); // Initialize with max possible changes
        }
        dp[n][0] = 0; // Base case: empty suffix into 0 parts costs 0

        for (int i = n - 1; i >= 0; i--) {
            for (int parts = 1; parts <= k; parts++) {
                // Substring must have length >= 2 (since 1 <= d < len requires len >= 2)
                for (int end = i + 1; end < n; end++) {
                    if (dp[end + 1][parts - 1] < n) {
                        dp[i][parts] = Math.min(dp[i][parts], cost[i][end] + dp[end + 1][parts - 1]);
                    }
                }
            }
        }

        return dp[0][k];
    }

    private List<Integer>[] getDivisors(int n) {
        @SuppressWarnings("unchecked")
        List<Integer>[] divisors = new List[n + 1];
        for (int len = 1; len <= n; len++) {
            divisors[len] = new ArrayList<>();
            for (int d = 1; d < len; d++) {
                if (len % d == 0) {
                    divisors[len].add(d);
                }
            }
        }
        return divisors;
    }

    private int[][] precomputeCosts(String s, int n, List<Integer>[] divisors) {
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int len = j - i + 1;
                int minChanges = len;
                
                for (int d : divisors[len]) {
                    minChanges = Math.min(minChanges, calculateChangesForD(s, i, j, d));
                }
                cost[i][j] = minChanges;
            }
        }
        return cost;
    }

    private int calculateChangesForD(String s, int start, int end, int d) {
        int changes = 0;
        // Check each of the 'd' modulo groups independently as a palindrome
        for (int offset = 0; offset < d; offset++) {
            int left = start + offset;
            // Find the rightmost element in this modulo group
            int right = end - (d - 1 - offset);
            while (left < right) {
                if (s.charAt(left) != s.charAt(right)) {
                    changes++;
                }
                left += d;
                right -= d;
            }
        }
        return changes;
    }
}