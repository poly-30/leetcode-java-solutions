class Solution {
    private int gcd(int a, int b){
        if(b == 0) return a;
        return gcd(b, a % b);

    }

    public int findGCD(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        return gcd(nums[n - 1], nums[0]);
    }
}