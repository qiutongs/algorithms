class Solution {
    public String removeOuterParentheses(String S) {
        if (S == null || S.isEmpty()) {
            return S;
        }
        StringBuilder sb = new StringBuilder();
        int numOpenP = 0;
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if ((c == '(' && numOpenP >= 1) || (c == ')' && numOpenP > 1)) {
                sb.append(c);
            }
            numOpenP += c == '(' ? 1 : -1;
        }
        return sb.toString();
    }
}

class Solution {
    public String removeOuterParentheses(String S) {
        if (S == null || S.isEmpty()) {
            return S;
        }
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (c == '(') {
                stack.push(c);
                if (stack.size() > 1) {
                    sb.append(c);
                }
            } else {
                stack.pop();
                if (stack.size() > 0) {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }
}