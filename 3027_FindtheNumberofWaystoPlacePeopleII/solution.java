import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public int numberOfPairs(int[][] points) {
        int n = points.length;
        int totalCount = 0;
        for (int i = 0; i < n; i++) {
            int[] pi = points[i];
            List<int[]> candidates = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                int[] pj = points[j];
                if (pj[0] >= pi[0] && pj[1] <= pi[1]) {
                    candidates.add(pj);
                }
            }
            Collections.sort(candidates, (a, b) -> {
                if (a[0] != b[0]) {
                    return Integer.compare(a[0], b[0]);
                } else {
                    return Integer.compare(b[1], a[1]);
                }
            });
            int maxY = Integer.MIN_VALUE;
            for (int[] candidate : candidates) {
                if (candidate[1] > maxY) {
                    totalCount++;
                  
                    maxY = candidate[1];
                }
            }
        }

        return totalCount;
    }
}