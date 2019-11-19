class Solution {
    public String decodeString(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        int[] offset = { 0 };
        dfs(s, offset, sb);
        return sb.toString();
    }
    
    private void dfs(String s, int[] offset, StringBuilder sb) {
        int num = 0;
        for (; offset[0] < s.length() && s.charAt(offset[0]) != ']'; offset[0]++) {
            char c = s.charAt(offset[0]);
            if (c == '[') {
                offset[0]++;
                StringBuilder childSb = new StringBuilder();
                dfs(s, offset, childSb);
                for (int i = 0; i < num; i++) {
                    sb.append(childSb.toString());
                }
                num = 0;
            } else if (Character.isDigit(c)) {
                num = num * 10 + (int)(c - '0');
            } else {
                sb.append(c);
            }
        }
    }
}