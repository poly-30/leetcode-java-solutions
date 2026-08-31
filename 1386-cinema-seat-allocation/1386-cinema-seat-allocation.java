import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rowMasks = new HashMap<>();
        
        // Build bitmask for each modified row
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            rowMasks.put(row, rowMasks.getOrDefault(row, 0) | (1 << col));
        }
        
        // Rows with no reservations can automatically fit 2 groups
        int totalGroups = (n - rowMasks.size()) * 2;
        
        // Process rows that have at least one reserved seat
        for (int mask : rowMasks.values()) {
            boolean left = (mask & 0b0000111100) == 0;   // seats 2, 3, 4, 5
            boolean right = (mask & 0b1111000000) == 0;  // seats 6, 7, 8, 9
            
            if (left && right) {
                totalGroups += 2;
            } else if (left || right || (mask & 0b0011110000) == 0) { // seats 4, 5, 6, 7
                totalGroups += 1;
            }
        }
        
        return totalGroups;
    }
}