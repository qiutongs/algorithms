class Solution {
    public List<String> removeInvalidParentheses(String s) {
        if (s == null || s.isEmpty()) {
            return Arrays.asList("");
        }
        List<String> ret = new ArrayList<>();
        dfs(s, 0, new StringBuilder(), 0, 0, ret);
        return ret;
    }
    
    private void dfs(String s, int offset, StringBuilder sb, int leftP, int rightP, List<String> ret) {
        if (leftP == rightP) {
            if (ret.isEmpty()) {
                ret.add(sb.toString());
            } else {
                if (ret.get(0).length() < sb.toString().length()) {
                    ret.clear();
                    ret.add(sb.toString());
                } else if (ret.get(0).length() == sb.toString().length()) {
                    ret.add(sb.toString());
                }
            }
        }
        for (int i = offset; i < s.length(); i++) {
            if (i > offset && s.charAt(i) == s.charAt(i - 1)) {
                continue;
            }
            int newLeftP = leftP + (s.charAt(i) == '(' ? 1 : 0);
            int newRightP = rightP + (s.charAt(i) == ')' ? 1 : 0);
            if (newLeftP >= newRightP) {
                sb.append(s.charAt(i));
                dfs(s, i + 1, sb, newLeftP, newRightP, ret);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}