class Solution {
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }
        List<List<Integer>> ret = new ArrayList<>();
        dfs(nums, new boolean[nums.length], new ArrayList<>(), ret);
        return ret;
    }
    
    private void dfs(int[] nums, boolean[] visited, List<Integer> curList, List<List<Integer>> ret) {
        if (curList.size() == nums.length) {
            ret.add(new ArrayList<>(curList));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) { 
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            curList.add(nums[i]);
            dfs(nums, visited, curList, ret);
            curList.remove(curList.size() - 1);
            visited[i] = false;
        }
    }
}