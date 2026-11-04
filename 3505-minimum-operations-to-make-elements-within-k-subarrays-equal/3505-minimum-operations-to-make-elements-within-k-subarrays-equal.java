import java.util.*;

class Solution {
    private static class MyMap {
        public TreeMap<Integer, Integer> map = new TreeMap<>();
        public int size = 0;
        public long sum = 0;
    }

    private static final long INF = Long.MAX_VALUE / 2;

    public long minOperations(int[] nums, int x, int k) {
        List<Long> minOps = getMinOps(nums, x);
        Long[][] mem = new Long[nums.length + 1][k + 1];
        return solve(minOps, x, 0, k, mem);
    }

    private long solve(List<Long> minOps, int x, int idx, int k, Long[][] mem) {
        if (k == 0) return 0;
        if (idx + (long) k * x > minOps.size() + x - 1) return INF;
        if (idx >= minOps.size()) return INF;
        if (mem[idx][k] != null) return mem[idx][k];

        // Option 1: Take a window starting at `idx`
        long take = minOps.get(idx) + solve(minOps, x, idx + x, k - 1, mem);
        // Option 2: Skip `idx`
        long skip = solve(minOps, x, idx + 1, k, mem);

        return mem[idx][k] = Math.min(take, skip);
    }

    private List<Long> getMinOps(int[] nums, int x) {
        List<Long> minOps = new ArrayList<>();
        MyMap lower = new MyMap();
        MyMap upper = new MyMap();

        for (int i = 0; i < nums.length; ++i) {
            // Add nums[i] to lower or upper
            int val = nums[i];
            if (lower.size == 0 || val <= lower.map.lastKey()) {
                lower.map.merge(val, 1, Integer::sum);
                lower.sum += val;
                lower.size++;
            } else {
                upper.map.merge(val, 1, Integer::sum);
                upper.sum += val;
                upper.size++;
            }

            // Balance the sizes
            balance(lower, upper);

            // Remove element that slides out of window
            if (i >= x) {
                int removeVal = nums[i - x];
                if (lower.map.containsKey(removeVal)) {
                    remove(lower, removeVal);
                } else {
                    remove(upper, removeVal);
                }
                balance(lower, upper);
            }

            // Calculate cost once window size is reached
            if (i >= x - 1) {
                int median = lower.map.lastKey();
                long ops = (1L * median * lower.size - lower.sum) + (upper.sum - 1L * median * upper.size);
                minOps.add(ops);
            }
        }
        return minOps;
    }

    private void balance(MyMap lower, MyMap upper) {
        while (lower.size > (lower.size + upper.size + 1) / 2) {
            int key = lower.map.lastKey();
            remove(lower, key);
            upper.map.merge(key, 1, Integer::sum);
            upper.sum += key;
            upper.size++;
        }
        while (lower.size < (lower.size + upper.size + 1) / 2) {
            int key = upper.map.firstKey();
            remove(upper, key);
            lower.map.merge(key, 1, Integer::sum);
            lower.sum += key;
            lower.size++;
        }
    }

    private void remove(MyMap m, int val) {
        m.sum -= val;
        m.size--;
        if (m.map.get(val) == 1) {
            m.map.remove(val);
        } else {
            m.map.put(val, m.map.get(val) - 1);
        }
    }
}
