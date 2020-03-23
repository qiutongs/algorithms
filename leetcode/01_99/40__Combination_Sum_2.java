/*
 * Note: this can work even input is not positive, not like Combination Sum 1
 */
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return Collections.emptyList();
        }
        Arrays.sort(candidates);
        List<List<Integer>> ret = new ArrayList<>();
        dfs(candidates, -1, target, new ArrayList<>(), ret);
        return ret;
    }
    
    private void dfs(int[] candidates, int index, int target, List<Integer> curList, List<List<Integer>> ret) {
        if (target == 0) {
            ret.add(new ArrayList<>(curList));
            return;
        }
        for (int i = index + 1; i < candidates.length; i++) {
            if (i > index + 1 && candidates[i] == candidates[i - 1]) {
                continue;
            }
            if (target - candidates[i] >= 0) {
                curList.add(candidates[i]);
                dfs(candidates, i, target - candidates[i], curList, ret);
                curList.remove(curList.size() - 1);
            }
        }
    }
}