class Solution {
    public int minimumPushes(String word) {
        int n = word.length();
        if (n <= 8) return n;
        
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int round = i / 8 + 1;   // which "press count" this letter costs
            ans += round;
        }
        return ans;
    }
}