import java.util.HashMap;

class Solution {
    public int totalFruit(int[] fruits) {
        // HashMap to keep count of fruit types in the window
        HashMap<Integer, Integer> basket = new HashMap<>();
        int left = 0, maxFruits = 0;
        
        for (int right = 0; right < fruits.length; right++) {
            // Add current fruit to the basket
            basket.put(fruits[right], basket.getOrDefault(fruits[right], 0) + 1);
            
            // If there are more than 2 types of fruits, shrink window from the left
            while (basket.size() > 2) {
                basket.put(fruits[left], basket.get(fruits[left]) - 1);
                if (basket.get(fruits[left]) == 0) {
                    basket.remove(fruits[left]);
                }
                left++;
            }
            
            // Update maxFruits if current window is larger
            maxFruits = Math.max(maxFruits, right - left + 1);
        }
        
        return maxFruits;
    }
}