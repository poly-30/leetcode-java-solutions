class Solution {
    public long minimumDifference(int[] nums) {
        int n = nums.length / 3;
        int len = nums.length;
        //left Sum --> max heap to track n smallest values
        PriorityQueue<Integer> leftMaxHeap = new PriorityQueue(Collections.reverseOrder());
        long[] leftSum = new long[len];
        long leftTotal = 0;
        for(int i = 0; i < len; ++i){
            leftTotal += nums[i];
            leftMaxHeap.offer(nums[i]);
            if(leftMaxHeap.size() > n){
                leftTotal -= leftMaxHeap.poll();    // remove the largest to keep n smallest
            }
            if(leftMaxHeap.size() == n){
                leftSum[i] = leftTotal;
            }else{
                leftSum[i] = Long.MAX_VALUE;
            }
        }

        //right max --> min heap to track n smallest values
        PriorityQueue<Integer> rightMinHeap = new PriorityQueue<>();
        long[] rightSum = new long[len];
        long rightTotal = 0;
        for(int i = len - 1; i >= 0; --i){
            rightTotal += nums[i];
            rightMinHeap.offer(nums[i]);
            if(rightMinHeap.size() > n){
                rightTotal -= rightMinHeap.poll();    // remove the smallest to keep n largest
            }
            if(rightMinHeap.size() == n){
                rightSum[i] = rightTotal;
            }else{
                rightSum[i] = Long.MAX_VALUE;
            }
        }

        long result = Long.MAX_VALUE;
        for(int i = n - 1; i < 2 * n; ++i){
            if(leftSum[i] != Long.MAX_VALUE && rightSum[i] != Long.MAX_VALUE){
                result = Math.min(result, leftSum[i] - rightSum[i+1]);
            }
        }
        return result;
    }
}