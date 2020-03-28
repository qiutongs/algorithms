// DFS + prune: 1 -> [10, 11, 12, ...]
class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> ret = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            dfs(i, n, ret);
        }
        return ret;
    }
    
    private void dfs(int cur, int n, List<Integer> ret) {
        if (cur > n) {
            return;
        }
        ret.add(cur);
        for (int i = 0; i <= 9; i++) {
            dfs(cur * 10 + i, n, ret);
        }
    }
}