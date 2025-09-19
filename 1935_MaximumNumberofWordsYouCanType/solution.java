import java.util.HashSet;
import java.util.Set;
class Solution {
    public int canBeTypedWords(String text, String brokenLetters) {
        Set<Character> brokenSet = new HashSet<>();
        for (char c : brokenLetters.toCharArray()) {
            brokenSet.add(c);
        }
        String[] words = text.split(" ");
        int typableWordsCount = 0;
        wordLoop:
        for (String word : words) {
            for (char c : word.toCharArray()) {
                if (brokenSet.contains(c)) {
                    continue wordLoop;
                }
            }
            typableWordsCount++;
        }

        return typableWordsCount;
    }
}