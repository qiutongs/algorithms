class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return Collections.emptyList();
        }
        List<List<Integer>> ret = new ArrayList<>();
        dfs(candidates, 0, target, new ArrayList<>(), ret);
        return ret;
    }
    
    private void dfs(int[] candidates, int offset, int target, List<Integer> curList, List<List<Integer>> ret) {
        if (target == 0) {
            ret.add(new ArrayList<>(curList));
        }
        if (target < 0) {
            return;
        }
        for (int i = offset; i < candidates.length; i++) {
            curList.add(candidates[i]);
            dfs(candidates, i, target - candidates[i], curList, ret);
            curList.remove(curList.size() - 1);
        }
    }
}