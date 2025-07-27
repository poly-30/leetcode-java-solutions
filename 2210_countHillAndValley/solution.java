class Solution {
    public int countHillValley(int[] nums) {
        ArrayList<Integer> uniqueNums = new ArrayList<>();
        if (nums.length == 0) {
            return 0;
        }
        uniqueNums.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != uniqueNums.get(uniqueNums.size() - 1)) {
                uniqueNums.add(nums[i]);
            }
        }
        if (uniqueNums.size() < 3) {
            return 0;
        }
        int count = 0;
        for (int i = 1; i < uniqueNums.size() - 1; i++) {
            int left = uniqueNums.get(i - 1);
            int current = uniqueNums.get(i);
            int right = uniqueNums.get(i + 1);
            boolean isHill = current > left && current > right;
            boolean isValley = current < left && current < right;
            if (isHill || isValley) {
                count++;
            }
        }
        return count;
    }
}