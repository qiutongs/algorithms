class Solution {
    public String removeOuterParentheses(String S) {
        if (S == null || S.isEmpty()) {
            return S;
        }
        StringBuilder sb = new StringBuilder();
        int numOpenP = 0;
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            numOpenP += c == '(' ? 1 : -1;
            if ((c == '(' && numOpenP == 1) || (c == ')' && numOpenP == 0)) {
                continue;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}