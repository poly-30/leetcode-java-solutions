class Solution {
    public int maximum69Number (int num) {
        // convert number to char array
        char[] digits = String.valueOf(num).toCharArray();
        
        // change the first '6' we encounter to '9'
        for (int i = 0; i < digits.length; i++) {
            if (digits[i] == '6') {
                digits[i] = '9';
                break; // only one change allowed
            }
        }
        
        // convert back to integer
        return Integer.parseInt(new String(digits));
    }
}
