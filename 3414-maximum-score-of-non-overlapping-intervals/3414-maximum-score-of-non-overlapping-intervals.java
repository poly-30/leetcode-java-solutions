import java.util.*;

class Solution {
    private static class Interval {
        int l, r, weight, originalIndex;

        Interval(int l, int r, int weight, int originalIndex) {
            this.l = l;
            this.r = r;
            this.weight = weight;
            this.originalIndex = originalIndex;
        }
    }

    private static class Result {
        long weightSum;
        List<Integer> indices;

        Result(long weightSum, List<Integer> indices) {
            this.weightSum = weightSum;
            this.indices = indices;
        }
    }

    private Interval[] intervalsArr;
    private Result[][] memo;
    private int n;

    public int[] maximumWeight(List<List<Integer>> intervals) {
        n = intervals.size();
        intervalsArr = new Interval[n];

        for (int i = 0; i < n; i++) {
            List<Integer> interval = intervals.get(i);
            intervalsArr[i] = new Interval(interval.get(0), interval.get(1), interval.get(2), i);
        }

        // Sort by starting point l_i
        Arrays.sort(intervalsArr, (a, b) -> Integer.compare(a.l, b.l));

        memo = new Result[n][5];

        Result bestResult = solve(0, 0);

        int[] ans = new int[bestResult.indices.size()];
        for (int i = 0; i < bestResult.indices.size(); i++) {
            ans[i] = bestResult.indices.get(i);
        }
        return ans;
    }

    private Result solve(int index, int count) {
        if (count == 4 || index == n) {
            return new Result(0, new ArrayList<>());
        }

        if (memo[index][count] != null) {
            return memo[index][count];
        }

        // Option 1: Skip current interval
        Result skip = solve(index + 1, count);

        // Option 2: Take current interval
        int nextIdx = binarySearchNext(intervalsArr[index].r);
        Result takeNext = solve(nextIdx, count + 1);

        long takeWeight = intervalsArr[index].weight + takeNext.weightSum;
        List<Integer> takeIndices = new ArrayList<>();
        takeIndices.add(intervalsArr[index].originalIndex);
        takeIndices.addAll(takeNext.indices);
        Collections.sort(takeIndices);

        Result take = new Result(takeWeight, takeIndices);

        // Compare Option 1 (skip) and Option 2 (take)
        Result best = getBetterResult(skip, take);

        memo[index][count] = best;
        return best;
    }

    private int binarySearchNext(int currentRight) {
        int low = 0, high = n - 1, ans = n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (intervalsArr[mid].l > currentRight) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private Result getBetterResult(Result r1, Result r2) {
        if (r1.weightSum > r2.weightSum) return r1;
        if (r2.weightSum > r1.weightSum) return r2;

        // If weight sums are equal, pick lexicographically smaller index list
        for (int i = 0; i < Math.min(r1.indices.size(), r2.indices.size()); i++) {
            int cmp = Integer.compare(r1.indices.get(i), r2.indices.get(i));
            if (cmp != 0) {
                return cmp < 0 ? r1 : r2;
            }
        }
        return r1.indices.size() <= r2.indices.size() ? r1 : r2;
    }
}