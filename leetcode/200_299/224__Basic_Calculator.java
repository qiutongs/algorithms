class Solution {
    public int calculate(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int[] offset = { 0 };
        return dfs(s, offset);
    }
    
    private int dfs(String s, int[] offset) {
        int ret = 0;
        int sign = 1;
        int num = 0;
        for (;offset[0] < s.length(); offset[0]++) {
            char c = s.charAt(offset[0]);
            if (c == '(') {
                offset[0]++;
                ret += sign * dfs(s, offset);
            } else if (c == ')') {
                break;
            } else if (c == '+' || c == '-') {
                ret += sign * num;
                sign = c == '+' ? 1 : -1;
                num = 0;
            } else if (Character.isDigit(c)) {
                num = num * 10 + (int)(c - '0');
            }
        }
        ret += sign * num;
        return ret;
    }
}