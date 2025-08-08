class Solution {
    public int maxCollectedFruits(int[][] fruits) {
        int n = fruits.length;
        int totalFruits = 0;
        //diagonal path : child from top-left to bottom-right
        for(int i=0; i<n; i++){
            totalFruits += fruits[i][i];
        }

        //Path from top-right to bottom-right
        totalFruits += computeMaxPath(fruits, n);

        //Transpose the grid to reuse the logic for bottom-left to bottom-right
        transpose(fruits);

        //Path from bottom-left to bottom-right( after transpose)
        totalFruits += computeMaxPath(fruits, n);
        return totalFruits;
    }

    //DP to compute maxFruits collected by one child avoiding the diagonal paths
    private int computeMaxPath(int[][] fruits, int n){
        int[] prev = new int[n];
        int[] curr = new int[n];
        Arrays.fill(prev, Integer.MIN_VALUE);
        Arrays.fill(curr, Integer.MIN_VALUE);
        prev[n-1] = fruits[0][n-1]; //start from top right corner
        for(int row = 1; row < n-1; row++){
            for(int col = Math.max(n-1-row, row+1); col<n; col++){
                int maxFruits = prev[col]; //upper
                if(col > 0) maxFruits = Math.max(maxFruits, prev[col-1]); //left upper
                if(col < n-1) maxFruits = Math.max(maxFruits, prev[col+1]); // right upper
                
                curr[col] = maxFruits + fruits[row][col];
            }
            //swap current and previous row arrays
            int[] temp = prev;
            prev = curr;
            curr = temp;
        }
        return prev[n-1];
    }

    //utility to transpose the matrix (important)
    private void transpose(int[][] matrix){
        int n = matrix.length;
        for(int i=0; i<n; i++){
            for(int j=0; j<i; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}