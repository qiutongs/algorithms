class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ret = new ArrayList<>();
        dfs(k, 0, n, new ArrayList<>(), ret);
        return ret;
    }
    
    private void dfs(int k, int cur, int n, List<Integer> curList, List<List<Integer>> ret) {
        if (n == 0 && k == 0) {
            ret.add(new ArrayList<>(curList));
            return;
        }
        for (int i = cur + 1; i <= 9; i++) {
            if (n - i >= 0) {
                curList.add(i);
                dfs(k - 1, i, n - i, curList, ret);
                curList.remove(curList.size() - 1);
            }
        }
    }
}