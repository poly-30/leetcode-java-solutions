import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
class Solution {
    public String sortVowels(String s) {
        List<Character> vowels = new ArrayList<>();
        for (char c : s.toCharArray()) {
            if (isVowel(c)) {
                vowels.add(c);
            }
        }
        Collections.sort(vowels);
        char[] resultChars = s.toCharArray();
        int vowelIndex = 0;
        for (int i = 0; i < resultChars.length; i++) {
            if (isVowel(resultChars[i])) {
                resultChars[i] = vowels.get(vowelIndex++);
            }
        }
        return new String(resultChars);
    }
    private boolean isVowel(char c) {
        return "aeiouAEIOU".indexOf(c) != -1;
    }
}