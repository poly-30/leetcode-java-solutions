class Solution {
    public String maxSumOfSquares(int num, int sum) {
        // Maximum possible sum for 'num' digits is 9 * num
        if (sum > 9 * num) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        
        int nines = sum / 9;
        int rem = sum % 9;

        // Fill leading digits with 9s
        for (int i = 0; i < nines; i++) {
            sb.append('9');
        }

        // Add the remainder digit if applicable
        if (nines < num && rem > 0) {
            sb.append(rem);
        }

        // Pad the remaining digits with 0s to reach length 'num'
        while (sb.length() < num) {
            sb.append('0');
        }

        return sb.toString();
    }
}