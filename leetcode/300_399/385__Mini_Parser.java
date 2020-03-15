class Solution {
    public NestedInteger deserialize(String s) {
        int[] index = { 0 };
        NestedInteger ret = new NestedInteger();
        dfs(s, index, ret);
        return ret.getList().get(0);
    }
    
    private void dfs(String s, int[] index, NestedInteger container) {
        Integer num = null;
        int sign = 1;
        for (; index[0] <= s.length(); index[0]++) {
            char c = index[0] < s.length() ? s.charAt(index[0]) : '\0';
            if (Character.isDigit(c)) {
                num = num == null ? 0 : num;
                num = num * 10 + (int)(c - '0');
            } else if (c == '-') {
                sign = -1;
            } else if (c == '[') {
                NestedInteger child = new NestedInteger();
                container.add(child);
                index[0]++;
                dfs(s, index, child);
            } else {
                if (num != null) {
                    container.add(new NestedInteger(sign * num));
                    num = null;
                    sign = 1;
                }
                if (c == ']') {
                    break;
                }
            }
        }
    }
}