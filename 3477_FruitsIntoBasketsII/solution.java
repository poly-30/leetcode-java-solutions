class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int n = fruits.length;
        boolean[] basket_available = new boolean[n];
        for (int i = 0; i < n; i++) {
            basket_available[i] = true;
        }
        int unplaced_fruits = 0;
        for (int i = 0; i < n; i++) {
            int current_fruit_quantity = fruits[i];
            boolean placed = false;
            for (int j = 0; j < n; j++) {
                if (basket_available[j] && baskets[j] >= current_fruit_quantity) {
                    // Found the leftmost available basket with sufficient capacity
                    basket_available[j] = false;
                    placed = true;
                    break; // Move to the next fruit
                }
            }
            if (!placed) {
            unplaced_fruits++;
            }
        }
        return unplaced_fruits;
    }
} // This is the closing brace for the Solution class.