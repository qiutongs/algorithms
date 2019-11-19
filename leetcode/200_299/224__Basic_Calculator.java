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
        for (;offset[0] < s.length() && s.charAt(offset[0]) != ')'; offset[0]++) {
            char c = s.charAt(offset[0]);
            if (c == '(') {
                offset[0]++;
                ret += sign * dfs(s, offset);
            } else if (c == '+' || c == '-') {
                sign = c == '+' ? 1 : -1;
            } else if (Character.isDigit(c)) {
                num = num * 10 + (int)(c - '0');
                if (offset[0] + 1 >= s.length() || Character.isDigit(s.charAt(offset[0] + 1)) == false) {
                    ret += sign * num;
                    num = 0;
                }
            }
        }
        return ret;
    }
}