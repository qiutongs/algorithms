class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return Collections.emptyList();
        }
        List<List<Integer>> ret = new ArrayList<>();
        dfs(candidates, 0, target, new ArrayList<>(), ret);
        return ret;
    }
    
    private void dfs(int[] candidates, int index, int target, List<Integer> curList, List<List<Integer>> ret) {
        if (target == 0) {
            ret.add(new ArrayList<>(curList));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (target - candidates[i] >= 0) {
                curList.add(candidates[i]);
                dfs(candidates, i, target - candidates[i], curList, ret);
                curList.remove(curList.size() - 1);
            }
        }
    }
}