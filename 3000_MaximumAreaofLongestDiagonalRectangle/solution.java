class Solution {
    public int areaOfMaxDiagonal(int[][] dimensions) {
        int maxDiagonalSquared = -1;
        int maxArea = 0;
        for (int[] rect : dimensions) {
            int length = rect[0];
            int width = rect[1];
            int currentDiagonalSquared = length * length + width * width;
            if (currentDiagonalSquared > maxDiagonalSquared) {
                maxDiagonalSquared = currentDiagonalSquared;
                maxArea = length * width;
            } 
            else if (currentDiagonalSquared == maxDiagonalSquared) {
                maxArea = Math.max(maxArea, length * width);
            }
        }

        return maxArea;
    }
}