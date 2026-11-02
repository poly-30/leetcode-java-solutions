class Solution {
    public boolean checkValidString(String s) {
        int minOpen = 0;
        int maxOpen = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                minOpen++;
                maxOpen++;
            } else if (c == ')') {
                minOpen--;
                maxOpen--;
            } else { // c == '*'
                minOpen--;
                maxOpen++;
            }

            // If maxOpen is negative, we have more ')' than possible '(' or '*'
            if (maxOpen < 0) {
                return false;
            }

            // minOpen cannot be negative
            if (minOpen < 0) {
                minOpen = 0;
            }
        }

        return minOpen == 0;
    }
}