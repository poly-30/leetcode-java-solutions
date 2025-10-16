class Solution {
    public int findSmallestInteger(int[] nums, int value) {
        Map<Integer, Integer> countMap = new HashMap<>();
        
        // Calculate frequency of modulo values
        for (int num : nums) {
            int mod = ((num % value) + value) % value; // handle negatives
            countMap.put(mod, countMap.getOrDefault(mod, 0) + 1);
        }
        
        int i = 0;
        while (true) {
            int mod = i % value;
            int count = countMap.getOrDefault(mod, 0);
            if (count == 0) {
                return i;
            }
            countMap.put(mod, count - 1);
            i++;
        }
    }
}
