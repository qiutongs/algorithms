class Solution {
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }
        List<List<Integer>> ret = new ArrayList<>();
        dfs(nums, 0, new ArrayList<>(), ret);
        return ret;
    }
    
    private void dfs(int[] nums, int offset, List<Integer> curList, List<List<Integer>> ret) {
        if (offset == nums.length) {
            ret.add(new ArrayList<>(curList));
        }
        
        for (int i = offset; i < nums.length; i++) {
            swap(nums, i, offset);
            curList.add(nums[offset]);
            dfs(nums, offset + 1, curList, ret);
            curList.remove(curList.size() - 1);
            swap(nums, i, offset);
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}