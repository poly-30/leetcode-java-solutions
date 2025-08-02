import java.util.*;

class Solution {
    public long minCost(int[] basket1, int[] basket2) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int x : basket1) freq.put(x, freq.getOrDefault(x, 0) + 1);
        for (int x : basket2) freq.put(x, freq.getOrDefault(x, 0) - 1);

        List<Integer> needSwap = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            // If any number has odd difference in total, impossible
            if ((count & 1) != 0) return -1;
            // Add abs(count)//2 times to swap list
            for (int i = 0; i < Math.abs(count) / 2; ++i)
                needSwap.add(num);
        }

        if (needSwap.isEmpty()) return 0;
        Collections.sort(needSwap);
        int minVal = Math.min(Arrays.stream(basket1).min().getAsInt(), Arrays.stream(basket2).min().getAsInt());

        long cost = 0;
        int n = needSwap.size();
        for (int i = 0; i < n / 2; ++i) {
            cost += Math.min(needSwap.get(i), minVal * 2);
        }
        return cost;
    }
}
