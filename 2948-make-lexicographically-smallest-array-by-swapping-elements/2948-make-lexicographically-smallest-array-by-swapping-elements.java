import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        int[][] paired = new int[n][2];
        for (int i = 0; i < n; i++) {
            paired[i] = new int[]{nums[i], i};
        }
        
        // Sort elements by their values
        Arrays.sort(paired, (a, b) -> Integer.compare(a[0], b[0]));

        int[] result = new int[n];
        int i = 0;

        while (i < n) {
            int j = i;
            List<Integer> indices = new ArrayList<>();
            // Group contiguous elements within limit
            while (j < n && (j == i || paired[j][0] - paired[j - 1][0] <= limit)) {
                indices.add(paired[j][1]);
                j++;
            }

            // Sort indices to place values lexicographically from left to right
            Collections.sort(indices);

            // Assign sorted values to sorted indices
            for (int k = 0; k < indices.size(); k++) {
                result[indices.get(k)] = paired[i + k][0];
            }

            i = j;
        }

        return result;
    }
}