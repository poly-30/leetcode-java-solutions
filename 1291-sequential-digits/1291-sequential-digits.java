import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new ArrayList<>();
        String digits = "123456789";
        
        int minLength = String.valueOf(low).length();
        int maxLength = String.valueOf(high).length();
        
        for (int length = minLength; length <= maxLength; length++) {
            for (int start = 0; start + length <= 9; start++) {
                String substring = digits.substring(start, start + length);
                int num = Integer.parseInt(substring);
                
                if (num >= low && num <= high) {
                    result.add(num);
                }
            }
        }
        
        return result;
    }
}
