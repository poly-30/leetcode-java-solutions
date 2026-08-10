class Solution {
    public boolean winnerSquareGame(int n) {
        Boolean[] memo = new Boolean[n + 1];
        return dfs(n, memo);
    }
    
    private boolean dfs(int stones, Boolean[] memo) {
        if (stones == 0) return false; // Base case: No moves left means a loss
        if (memo[stones] != null) return memo[stones];
        
        for (int j = 1; j * j <= stones; j++) {
            // If the next turn puts the opponent in a losing state, we win
            if (!dfs(stones - j * j, memo)) {
                return memo[stones] = true;
            }
        }
        
        return memo[stones] = false;
    }
}
