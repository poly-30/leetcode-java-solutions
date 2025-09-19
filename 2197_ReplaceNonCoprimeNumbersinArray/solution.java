import java.util.LinkedList;
import java.util.List;

class Solution {
    public List<Integer> replaceNonCoprimes(int[] nums) {
        LinkedList<Integer> result = new LinkedList<>();

        for (int num : nums) {
            long currentNum = num;
            while (!result.isEmpty()) {
                long top = result.getLast();
                long commonDivisor = gcd(top, currentNum);

                if (commonDivisor == 1) {
                    break;
                }
                result.removeLast();
                currentNum = (top * currentNum) / commonDivisor;
            }
            result.add((int)currentNum);
        }
        
        return result;
    }
    private long gcd(long a, long b) {
        while (b > 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}