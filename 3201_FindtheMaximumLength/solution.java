class Solution {
    public int maximumLength(int[] nums) {
        int n = nums.length;
        int odd = 0;
        int even = 0;
        int evenodd = 0;
        int oldParity = 0;
        for(int i = 0; i < n; ++i){
            if(nums[i] % 2 == 0){
                even++;
            }
            else if(nums[i] %2 == 1){
                odd++;
            }

            int parity = nums[i] % 2 == 0?0:1;

            if(i == 0 || parity != oldParity){
                evenodd++;
                oldParity = nums[i] % 2 == 0?0:1;
            }
        }
        return Math.max(Math.max(even,odd),evenodd);        
    }
}