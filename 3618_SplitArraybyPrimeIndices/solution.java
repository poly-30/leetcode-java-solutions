class Solution {
    public long splitArray(int[] nums) {
        int n = nums.length;

        if(n == 0){
            return 0;
        }

        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);

        if(n > 0){
            isPrime[0] = false;
        }

        if(n > 1){
            isPrime[1] = false;
        }

        for(int p = 2; p * p < n; ++p){
            if(isPrime[p]){
                for(int i = p * p; i < n; i += p){
                    isPrime[i] = false;
                }
            }
        }

        long sumA = 0;
        long sumB = 0;

        for(int i = 0; i < n; ++i){
            if(isPrime[i]){
                sumA += nums[i];
            }else{
                sumB +=nums[i];
            }
        }

        return Math.abs(sumA - sumB);
    }
}