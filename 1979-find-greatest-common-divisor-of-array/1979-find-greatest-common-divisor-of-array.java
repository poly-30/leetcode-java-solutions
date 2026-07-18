class Solution {
    private int gcd(int a, int b){
        if(b == 0) return a;
        return gcd(b, a % b);

    }
    
    int min(int[] arr){
        int min = arr[0];
        for(int i : arr){
            if(min > i) min = i;
        }
        return min;
    }

    int max(int[] arr){
        int max = arr[0];
        for(int i : arr){
            if(max < i) max = i;
        }
        return max;
    }

    public int findGCD(int[] nums) {
        int min = min(nums);
        int max = max(nums);
        return gcd(max, min);
    }
}