class Solution {
    public String decodeString(String s) {
        StringBuilder ret = new StringBuilder();
        dfs(ret, s, 0);
        return ret.toString();
    }
    
    private int dfs(StringBuilder ret, String s, int index) {
        while(index < s.length() && s.charAt(index) != ']') {
            char c = s.charAt(index);
            
            if (Character.isDigit(c)) {
                int k = 0;
                while(s.charAt(index) != '[') {
                    k = k * 10 + s.charAt(index) - '0';
                    index++;
                }
                
                StringBuilder sb = new StringBuilder();
                index = dfs(sb, s, index + 1);
                
                for (int i = 0; i < k; i++) {
                    ret.append(sb.toString());
                }
            } else {
                ret.append(c);
                index++;
            }
        }
        
        return index + 1;
    }
}