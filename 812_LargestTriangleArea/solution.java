class Solution {
    public double largestTriangleArea(int[][] points) {
        double maxArea = 0;
        int n = points.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    int[] A = points[i], B = points[j], C = points[k];
                    double area = Math.abs(
                        A[0] * (B[1] - C[1]) +
                        B[0] * (C[1] - A[1]) +
                        C[0] * (A[1] - B[1])
                    ) / 2.0;
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }
}
