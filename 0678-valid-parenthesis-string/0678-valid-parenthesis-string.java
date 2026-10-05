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

            // If maxOpen is negative, we have more ')' than possible '(' + '*'
            if (maxOpen < 0) {
                return false;
            }

            // minOpen cannot be negative since we can't have negative open brackets
            minOpen = Math.max(0, minOpen);
        }

        // Valid if we can reach 0 open brackets
        return minOpen == 0;
    }
}