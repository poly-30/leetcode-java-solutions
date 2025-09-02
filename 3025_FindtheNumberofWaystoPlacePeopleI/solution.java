class Solution {
    /**
     * Finds the number of pairs of people (i, j) that can be placed without
     * any other person k being obstructed.
     *
     * @param points A 2D array where points[i] = [xi, yi].
     * @return The number of valid, unobstructed pairs.
     */
    public int numberOfPairs(int[][] points) {
        int n = points.length;
        int validPairsCount = 0;

        // Iterate through every possible starting point 'i'
        for (int i = 0; i < n; i++) {
            // Iterate through every possible ending point 'j'
            for (int j = 0; j < n; j++) {
                // Points must be distinct
                if (i == j) {
                    continue;
                }

                int[] p1 = points[i];
                int[] p2 = points[j];

                // 1. Check the placement condition: p1 must be top-left of p2
                if (p1[0] <= p2[0] && p1[1] >= p2[1]) {
                    
                    // 2. Assume no obstruction initially
                    boolean isObstructed = false;
                    
                    // 3. Check every other point 'k' for obstruction
                    for (int k = 0; k < n; k++) {
                        // The obstructing point cannot be p1 or p2
                        if (k == i || k == j) {
                            continue;
                        }

                        int[] p3 = points[k];

                        // Check if p3 is within the bounding box formed by p1 and p2
                        if (p3[0] >= p1[0] && p3[0] <= p2[0] && 
                            p3[1] >= p2[1] && p3[1] <= p1[1]) {
                            
                            isObstructed = true;
                            break; // Found an obstruction, no need to check further for this pair
                        }
                    }

                    // 4. If no obstruction was found, increment the count
                    if (!isObstructed) {
                        validPairsCount++;
                    }
                }
            }
        }

        return validPairsCount;
    }
}