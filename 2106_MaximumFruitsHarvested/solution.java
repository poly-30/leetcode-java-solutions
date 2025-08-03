class Solution {
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int n = fruits.length;
        int[] pos = new int[n];
        int[] pre = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            pos[i] = fruits[i][0];
            pre[i + 1] = pre[i] + fruits[i][1];
        }

        int res = 0;

        // Walk left then right
        for (int l = 0; l < n; ++l) {
            int left = pos[l];
            int stepsToLeft = Math.max(0, startPos - left);
            if (stepsToLeft > k) continue;
            int rest = k - 2 * stepsToLeft; // go left, then right, come back
            int right = startPos + Math.max(rest, (k-stepsToLeft)/2);
            int r = upperBound(pos, right);
            res = Math.max(res, pre[r] - pre[l]);
        }
        // Walk right then left
        for (int r = 0; r < n; ++r) {
            int right = pos[r];
            int stepsToRight = Math.max(0, right - startPos);
            if (stepsToRight > k) continue;
            int rest = k - 2 * stepsToRight;
            int left = startPos - Math.max(rest, (k-stepsToRight)/2);
            int l = lowerBound(pos, left);
            res = Math.max(res, pre[r + 1] - pre[l]);
        }

        return res;
    }

    // Find the first index where pos[index] > target
    private int upperBound(int[] pos, int target) {
        int l = 0, r = pos.length;
        while (l < r) {
            int m = (l + r) / 2;
            if (pos[m] > target) r = m;
            else l = m + 1;
        }
        return l;
    }

    // Find the first index where pos[index] >= target
    private int lowerBound(int[] pos, int target) {
        int l = 0, r = pos.length;
        while (l < r) {
            int m = (l + r) / 2;
            if (pos[m] >= target) r = m;
            else l = m + 1;
        }
        return l;
    }
}
