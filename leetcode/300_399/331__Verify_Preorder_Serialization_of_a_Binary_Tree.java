class Solution {
    public boolean isValidSerialization(String preorder) {
        if (preorder == null || preorder.isEmpty()) {
            return false;
        }
        String[] tokens = preorder.split(",");
        int[] index = { 0 };
        dfs(tokens, index);
        return index[0] == tokens.length - 1;
    }
    
    // index stops at the end of current subtree
    private void dfs(String[] tokens, int[] index) {
        if (index[0] >= tokens.length) {
            return;
        }
        if (tokens[index[0]].equals("#")) {
            return;
        }
        index[0]++;
        dfs(tokens, index);
        index[0]++;
        dfs(tokens, index);
    }
}