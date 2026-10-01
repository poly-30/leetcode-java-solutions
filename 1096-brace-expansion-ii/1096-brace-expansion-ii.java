import java.util.*;

class Solution {
    public List<String> braceExpansionII(String expression) {
        Stack<Set<String>> operandStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();
        
        int n = expression.length();
        
        for (int i = 0; i < n; i++) {
            char c = expression.charAt(i);
            
            // Insert implicit concatenation operator if applicable
            if (i > 0) {
                char prev = expression.charAt(i - 1);
                if ((Character.isLetter(prev) || prev == '}') && (Character.isLetter(c) || c == '{')) {
                    while (!operatorStack.isEmpty() && operatorStack.peek() == '.') {
                        evaluateTop(operandStack, operatorStack);
                    }
                    operatorStack.push('.');
                }
            }
            
            if (Character.isLetter(c)) {
                Set<String> set = new TreeSet<>();
                set.add(String.valueOf(c));
                operandStack.push(set);
            } else if (c == '{') {
                operatorStack.push(c);
            } else if (c == ',') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '{') {
                    evaluateTop(operandStack, operatorStack);
                }
                operatorStack.push(',');
            } else if (c == '}') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '{') {
                    evaluateTop(operandStack, operatorStack);
                }
                operatorStack.pop(); // Pop '{'
            }
        }
        
        while (!operatorStack.isEmpty()) {
            evaluateTop(operandStack, operatorStack);
        }
        
        return new ArrayList<>(operandStack.pop());
    }
    
    private void evaluateTop(Stack<Set<String>> operandStack, Stack<Character> operatorStack) {
        char op = operatorStack.pop();
        Set<String> set2 = operandStack.pop();
        Set<String> set1 = operandStack.pop();
        
        Set<String> res = new TreeSet<>();
        if (op == ',') {
            res.addAll(set1);
            res.addAll(set2);
        } else if (op == '.') { // Concatenation
            for (String s1 : set1) {
                for (String s2 : set2) {
                    res.add(s1 + s2);
                }
            }
        }
        operandStack.push(res);
    }
}