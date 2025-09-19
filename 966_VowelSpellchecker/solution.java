import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public String[] spellchecker(String[] wordlist, String[] queries) {
        // Rule 1: For exact, case-sensitive matches.
        Set<String> exactWords = new HashSet<>(Arrays.asList(wordlist));

        // Rule 2: For case-insensitive matches.
        // Key: lowercase word, Value: original word (first occurrence)
        Map<String, String> caseInsensitiveMap = new HashMap<>();

        // Rule 3: For vowel errors.
        // Key: lowercase word with vowels replaced, Value: original word (first occurrence)
        Map<String, String> vowelErrorMap = new HashMap<>();

        // Pre-process the wordlist to populate the maps.
        for (String word : wordlist) {
            String lower = word.toLowerCase();
            String devoweled = getDevoweledPattern(lower);

            // putIfAbsent ensures we only store the *first* occurrence as per the rules.
            caseInsensitiveMap.putIfAbsent(lower, word);
            vowelErrorMap.putIfAbsent(devoweled, word);
        }

        String[] result = new String[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];

            // Apply rules in order of priority.
            if (exactWords.contains(query)) {
                // Rule 1: Exact match found.
                result[i] = query;
                continue;
            }
            
            String lowerQuery = query.toLowerCase();
            if (caseInsensitiveMap.containsKey(lowerQuery)) {
                // Rule 2: Case-insensitive match found.
                result[i] = caseInsensitiveMap.get(lowerQuery);
                continue;
            }
            
            String devoweledQuery = getDevoweledPattern(lowerQuery);
            if (vowelErrorMap.containsKey(devoweledQuery)) {
                // Rule 3: Vowel error match found.
                result[i] = vowelErrorMap.get(devoweledQuery);
                continue;
            }
            
            // No match found by any rule.
            result[i] = "";
        }

        return result;
    }

    /**
     * Creates a pattern for a word by replacing its vowels with a placeholder '*'.
     * The input word is expected to be lowercase.
     */
    private String getDevoweledPattern(String word) {
        StringBuilder sb = new StringBuilder();
        for (char c : word.toCharArray()) {
            sb.append(isVowel(c) ? '*' : c);
        }
        return sb.toString();
    }

    /**
     * Helper to check if a character is a vowel (a, e, i, o, u).
     */
    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}