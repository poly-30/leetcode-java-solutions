class Solution {
    public int minInsertions(String s) {
        int ans = 0;
        int open = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                if (open % 2 != 0) {
                    ans++;
                    open--;
                }
                open += 2;
            } else {
                open--;
                if (open < 0) {
                    ans++;
                    open += 2;
                }
            }
        }
        
        return ans + open;
    }
}