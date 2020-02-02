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

// passed most of tests but wrong: "([)]"
class Solution {
    public boolean isValid(String s) {
        int lp1 = 0, rp1 = 0, lp2 = 0, rp2 = 0, lp3 = 0, rp3 = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch(c) {
                case '(': lp1++; break;
                case ')': rp1++; break;
                case '[': lp2++; break;
                case ']': rp2++; break;
                case '{': lp3++; break;
                case '}': rp3++; break;
            }
            if (rp1 > lp1 || rp2 > lp2 || rp3 > lp3) {
                return false;
            }
        }
        return rp1 == lp1 && rp2 == lp2 && rp3 == lp3;
    }
}