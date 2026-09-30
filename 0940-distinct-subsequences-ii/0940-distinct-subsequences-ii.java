class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        long[] last = new long[26];
        
        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            
            long currentTotal = 0;
            for (long count : last) {
                currentTotal = (currentTotal + count) % MOD;
            }
            
            last[idx] = (currentTotal + 1) % MOD;
        }
        
        long result = 0;
        for (long count : last) {
            result = (result + count) % MOD;
        }
        
        return (int) result;
    }
}