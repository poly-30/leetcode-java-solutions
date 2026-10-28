import java.util.ArrayList;
import java.util.List;

class Solution {
    private static final long MOD = 1_000_000_007L;
    private long[][] table;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        
        // Precompute Pascal's Triangle for combinations nCr
        table = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            table[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                table[i][j] = (table[i - 1][j - 1] + table[i - 1][j]) % MOD;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }

        // Subtract 1 to exclude the original array order from the final count
        return (int) ((getWays(list) - 1 + MOD) % MOD);
    }

    private long getWays(List<Integer> nums) {
        if (nums.size() <= 2) {
            return 1;
        }

        int root = nums.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        // Segregate elements into left and right subtrees
        for (int i = 1; i < nums.size(); i++) {
            int current = nums.get(i);
            if (current < root) {
                left.add(current);
            } else {
                right.add(current);
            }
        }

        // Recursively compute ways for subtrees
        long leftWays = getWays(left);
        long rightWays = getWays(right);

        // Merge using combinations: choose positions for left elements out of total slots
        long totalSlots = left.size() + right.size();
        long combinations = table[(int) totalSlots][left.size()];

        // Multiply results together modulo 10^9 + 7
        return (((combinations * leftWays) % MOD) * rightWays) % MOD;
    }
}
