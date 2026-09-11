import java.util.HashSet;
import java.util.Set;

class Solution {
    public int totalNumbers(int[] digits) {
        Set<Integer> uniqueEvens = new HashSet<>();
        int n = digits.length;

        for (int i = 0; i < n; i++) {
            if (digits[i] == 0) continue; // No leading zero
            
            for (int j = 0; j < n; j++) {
                if (i == j) continue; // Must use a different copy of the digit
                
                for (int k = 0; k < n; k++) {
                    if (k == i || k == j) continue;
                    
                    // Check if unit digit is even
                    if (digits[k] % 2 == 0) {
                        int num = digits[i] * 100 + digits[j] * 10 + digits[k];
                        uniqueEvens.add(num);
                    }
                }
            }
        }

        return uniqueEvens.size();
    }
}