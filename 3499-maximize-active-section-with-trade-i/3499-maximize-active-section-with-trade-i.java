public class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int oneCount = 0;
        int convertedOne = 0;
        int curZeroCount = 0;
        int lastZeroCount = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '0') {
                curZeroCount++;
            } else {
                if (curZeroCount != 0) {
                    lastZeroCount = curZeroCount;
                }
                curZeroCount = 0;
                oneCount++;
            }
            convertedOne = Math.max(convertedOne, curZeroCount + lastZeroCount);
        }
        // corner case
        if (convertedOne == curZeroCount || convertedOne == lastZeroCount) {
            return oneCount;
        }
        return oneCount + convertedOne;
    }
}