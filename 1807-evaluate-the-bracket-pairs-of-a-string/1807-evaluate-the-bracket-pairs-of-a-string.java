import java.util.*;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        StringBuilder result = new StringBuilder();
        StringBuilder currentKey = new StringBuilder();
        boolean insideBracket = false;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                insideBracket = true;
            } else if (ch == ')') {
                insideBracket = false;
                String key = currentKey.toString();
                result.append(map.getOrDefault(key, "?"));
                currentKey.setLength(0);
            } else {
                if (insideBracket) {
                    currentKey.append(ch);
                } else {
                    result.append(ch);
                }
            }
        }

        return result.toString();
    }
}