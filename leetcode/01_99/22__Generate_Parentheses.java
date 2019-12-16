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

class Solution {
    private HashMap<Integer, List<String>> memo = new HashMap<>();
    public List<String> generateParenthesis(int n) {
        if (n == 0) {
            return Arrays.asList("");
        }
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        List<String> ret = new ArrayList<>();
        for (int i = 0, j = n - 1; i < n; i++, j--) {
            for (String s : generateParenthesis(i)) {
                for (String t : generateParenthesis(j)) {
                    ret.add("(" + s + ")" + t);
                }
            }
        }
        memo.put(n, ret);
        return ret;
    }
}