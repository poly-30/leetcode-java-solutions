class Solution {
    public int minAddToMakeValid(String s) {
        int openParen = 0;
        int additionsNeeded = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                openParen++;
            } else {
                if (openParen > 0) {
                    openParen--;
                } else {
                    additionsNeeded++;
                }
            }
        }
        return additionsNeeded + openParen;
    }
}