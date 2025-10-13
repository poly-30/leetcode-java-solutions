import java.util.*;

class Solution {
    public List<String> removeAnagrams(String[] words) {
        List<String> result = new ArrayList<>();
        String prevSorted = "";
        for (String word : words) {
            char[] arr = word.toCharArray();
            Arrays.sort(arr);
            String currSorted = new String(arr);
            if (!currSorted.equals(prevSorted)) {
                result.add(word);
            }
            prevSorted = currSorted;
        }
        return result;
    }
}
