class Solution {
    private int[] digitsum(int n){
        int[] s = new int[2];
        s[0] = n % 10;
        s[1] = n % 10;
        n /= 10;
        while(n > 0){
            s[0] += n % 10;
            s[1] *= n % 10;
            n /= 10;
        }
        return s;
    }
    public boolean checkDivisibility(int n) {
        int[] sum = digitsum(n);
        return n % (sum[0] + sum[1]) == 0;
    }
}