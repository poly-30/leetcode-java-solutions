class Solution {
    public int maximumGain(String s, int x, int y) {
        if (x < y) {
            int temp = x;
            x = y;
            y = temp;
            s = new StringBuilder(s).reverse().toString();
        }

        int totalScore = 0;

        StringBuilder sAfterFirstPass = new StringBuilder();
        for (char ch : s.toCharArray()) {

            if (ch == 'b' && sAfterFirstPass.length() > 0 && sAfterFirstPass.charAt(sAfterFirstPass.length() - 1) == 'a') {
                sAfterFirstPass.deleteCharAt(sAfterFirstPass.length() - 1);
                totalScore += x;
            } else {
                sAfterFirstPass.append(ch);
            }
        }

        StringBuilder sAfterSecondPass = new StringBuilder();
        for (char ch : sAfterFirstPass.toString().toCharArray()) {

            if (ch == 'a' && sAfterSecondPass.length() > 0 && sAfterSecondPass.charAt(sAfterSecondPass.length() - 1) == 'b') {
                sAfterSecondPass.deleteCharAt(sAfterSecondPass.length() - 1);
                totalScore += y;
            } else {
                sAfterSecondPass.append(ch);
            }
        }

        return totalScore;
    }
}