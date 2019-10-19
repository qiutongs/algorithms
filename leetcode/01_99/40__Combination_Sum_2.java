/*
 * Note: I think it is not necessarily needs to be positive
 */
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return Collections.emptyList();
        }
        
        List<List<Integer>> result = new ArrayList<>();
        
        Arrays.sort(candidates);
        
        dfs(result, new ArrayList<>(), candidates, 0, target);
        
        return result;
    }
    
    private void dfs(List<List<Integer>> result, List<Integer> comb, int[] candidates, int i, int target) {
        if (target < 0) {
            return;
        }
        
        if (target == 0) {
            result.add(new ArrayList<>(comb));
        }
        
        for (int j = i; j < candidates.length; j++) {
            if (j > i && candidates[j] == candidates[j - 1]) {
                continue;
            }
            
            comb.add(candidates[j]);
            dfs(result, comb, candidates, j + 1, target - candidates[j]);
            comb.remove(comb.size() - 1);
        }
    }
}