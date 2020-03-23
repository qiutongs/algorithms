class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }
        List<List<Integer>> ret = new ArrayList<>();
        dfs(nums, -1, new ArrayList<>(), ret);
        return ret;
    }
    
    private void dfs(int[] nums, int index, List<Integer> curList, List<List<Integer>> ret) {
        if (curList.size() >= 2) {
            ret.add(new ArrayList<>(curList));
        }
        HashSet<Integer> visited = new HashSet<>();
        for (int i = index + 1; i < nums.length; i++) {
            if (visited.contains(nums[i])) {
                continue;
            }
            visited.add(nums[i]);
            if (curList.isEmpty() || curList.get(curList.size() - 1) <= nums[i]) {
                curList.add(nums[i]);
                dfs(nums, i, curList, ret);
                curList.remove(curList.size() - 1);
            }
        }
    }
}

