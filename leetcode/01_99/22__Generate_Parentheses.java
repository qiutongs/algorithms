class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ret = new ArrayList<>();
        dfs(n, 0, 0, new StringBuilder(), ret);
        return ret;
    }
    
    private void dfs(int n, int open, int close, StringBuilder sb, List<String> ret) {
        if (open == n && close == n) {
            ret.add(sb.toString());
            return;
        }
        
        if (open < n) {
            sb.append("(");
            dfs(n, open + 1, close, sb, ret);
            sb.deleteCharAt(sb.length() - 1);
        }
        
        if (close < n && open > close) {
            sb.append(")");
            dfs(n, open, close + 1, sb, ret);
            sb.deleteCharAt(sb.length() - 1);
        } 
    }
}