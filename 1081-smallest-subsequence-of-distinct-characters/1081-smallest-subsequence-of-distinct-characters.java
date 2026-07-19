import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public String smallestSubsequence(String s) {
        // Track the last occurrence index of each character
        int[] lastIndex = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }
        
        // Track whether a character is currently in our stack
        boolean[] seen = new boolean[26];
        Deque<Character> stack = new ArrayDeque<>();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            // Skip the character if it is already included in our result path
            if (seen[c - 'a']) {
                continue;
            }
            
            // Maintain monotonic increasing order if possible:
            // Pop top character if it's larger than current, and appears again later
            while (!stack.isEmpty() && stack.peek() > c && lastIndex[stack.peek() - 'a'] > i) {
                seen[stack.pop() - 'a'] = false;
            }
            
            // Push current character to stack and mark it as seen
            stack.push(c);
            seen[c - 'a'] = true;
        }
        
        // Build the final string from the stack contents
        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }
        
        // Since ArrayDeque iteration goes top-to-bottom, we must reverse it
        return sb.reverse().toString();
    }
}
