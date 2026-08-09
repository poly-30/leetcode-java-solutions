class Solution {
    private int[] suffixSum;
    private int[][] memo;
    private int n;

    public int stoneGameII(int[] piles) {
        this.n = piles.length;
        this.suffixSum = new int[n + 1];
        
        // Step 1: Precompute suffix sums from right to left
        for (int i = n - 1; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }
        
        // Step 2: Initialize memoization table
        // Max value of M can technically grow, but it never needs to exceed n
        this.memo = new int[n][n + 1];
        
        // Step 3: Start the game from index 0 with M = 1
        return get_max_stones(0, 1);
    }

    private int get_max_stones(int i, int m) {
        // Base case: If the player can take all remaining piles
        if (i + 2 * m >= n) {
            return suffixSum[i];
        }
        
        // Return cached result if already calculated
        if (memo[i][m] != 0) {
            return memo[i][m];
        }
        
        int maxStones = 0;
        
        // Explore all choices: take X piles where 1 <= X <= 2M
        for (int x = 1; x <= 2 * m; x++) {
            // Opponent plays optimally from the next state
            int opponentStones = get_max_stones(i + x, Math.max(m, x));
            
            // Current player's score is total remaining minus opponent's best score
            int currentStones = suffixSum[i] - opponentStones;
            
            maxStones = Math.max(maxStones, currentStones);
        }
        
        // Cache and return the optimal score for this state
        memo[i][m] = maxStones;
        return maxStones;
    }
}
