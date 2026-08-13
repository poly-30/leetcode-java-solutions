class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        // Variables to remember if the first row/col need to be zeroed out at the end
        boolean firstRowHasZero = false;
        boolean firstColHasZero = false;
        
        // 1. Check if the first column has any zeros
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                firstColHasZero = true;
                break;
            }
        }
        
        // 2. Check if the first row has any zeros
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                firstRowHasZero = true;
                break;
            }
        }
        
        // 3. Use the first row and first col as markers for the rest of the matrix
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0; // Mark this row
                    matrix[0][j] = 0; // Mark this col
                }
            }
        }
        
        // 4. Use the markers to zero out the cells (skip row 0 and col 0 for now)
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        
        // 5. Finally, zero out the first column if needed
        if (firstColHasZero) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
        
        // 6. Finally, zero out the first row if needed
        if (firstRowHasZero) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }
    }
}
