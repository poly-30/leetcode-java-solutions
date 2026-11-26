class Solution {
    public boolean canAliceWin(int n) {
        return (n >= 10 && n <= 18) || 
               (n >= 27 && n <= 33) || 
               (n >= 40 && n <= 44) || 
               (n >= 49 && n <= 50);
    }
}