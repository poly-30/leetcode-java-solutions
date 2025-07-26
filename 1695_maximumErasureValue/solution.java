class Solution {
    public int maximumUniqueSubarray(int[] nums) {
        Set<Integer> seen = new HashSet<>();

        int left = 0, right = 0, currentSum = 0, maxSum = 0;

        while(right < nums.length){
            int num = nums[right];

            while(seen.contains(num)){
                seen.remove(nums[left]);
                currentSum -= nums[left];
                left += 1;
            }
            seen.add(num);
            currentSum += num;
            maxSum = Math.max(maxSum, currentSum);
            right += 1;
        }
        return maxSum;
    }

}