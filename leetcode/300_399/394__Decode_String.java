class Solution {
    public String decodeString(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        dfs(s, new int[]{0}, sb);
        return sb.toString();
    }
    
    private void dfs(String s, int[] index, StringBuilder sb) {
        int num = 0;
        for (; index[0] < s.length(); index[0]++) {
            char c = s.charAt(index[0]);
            if (Character.isDigit(c)) {
                num = num * 10 + (int)(c - '0');
            } else if (c == '[') {
                StringBuilder childSb = new StringBuilder();
                index[0]++;
                dfs(s, index, childSb);
                for (int i = 0; i < num; i++) {
                    sb.append(childSb.toString());
                }
                num = 0;
            } else if (c == ']') {
                break;
            } else {
                sb.append(c);
            }
        }
    }
}