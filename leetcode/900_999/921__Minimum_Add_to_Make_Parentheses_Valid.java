class Solution {
    public int minAddToMakeValid(String S) {
        if (S == null || S.isEmpty()) {
            return 0;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (stack.isEmpty() == false && stack.peek().charValue() == '(' && c == ')') {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        return stack.size();
    }
}