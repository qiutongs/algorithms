class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isClosing(c)) {
                if (stack.isEmpty() == false && isMatching(stack.peek(), c)) {
                    stack.pop();
                } else {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
    
    private boolean isClosing(char c) {
        return c == '}' || c == ']' || c == ')';
    }
    
    private boolean isMatching(char c1, char c2) {
        return (c1 == '{' && c2 == '}') || (c1 == '[' && c2 == ']') || (c1 == '(' && c2 == ')');
    }
}