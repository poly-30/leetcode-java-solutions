import java.util.HashMap;
import java.util.Map;

class Solution {
    private Map<String, Double> memo = new HashMap<>();
    
    public double soupServings(int n) {
        // For large n, probability approaches 1
        if (n > 4800) {
            return 1.0;
        }
        
        // Scale n down by 25mL units (ceiling)
        int scaledN = (n + 24) / 25;
        return dp(scaledN, scaledN);
    }
    
    private double dp(int a, int b) {
        if (a <= 0 && b <= 0) return 0.5;   // Both empty at the same time
        if (a <= 0) return 1.0;             // A empty first
        if (b <= 0) return 0.0;             // B empty first
        
        String key = a + "," + b;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        
        double result = 0.25 * (
            dp(a - 4, b) +
            dp(a - 3, b - 1) +
            dp(a - 2, b - 2) +
            dp(a - 1, b - 3)
        );
        
        memo.put(key, result);
        return result;
    }
}
