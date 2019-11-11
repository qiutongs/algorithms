class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }
        List<List<Integer>> ret = new ArrayList<>();
        dfs(nums, 0, new ArrayList<>(), ret);
        return ret;
    }
    
    private void dfs(int[] nums, int offset, List<Integer> curList, List<List<Integer>> ret) {
        if (curList.size() >= 2) {
            ret.add(new ArrayList<>(curList));
        }
        for (int i = offset; i < nums.length; i++) {
            if (indexOf(nums, offset, i - 1, nums[i]) >= 0) {
                continue;
            }
            if (curList.isEmpty() || curList.get(curList.size() - 1) <= nums[i]) {
                curList.add(nums[i]);
                dfs(nums, i + 1, curList, ret);
                curList.remove(curList.size() - 1);
            }
        }
    }
    
    private int indexOf(int[] nums, int l, int r, int target) {
        for (int i = l; i <= r; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }
}

class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }
        
        List<List<Integer>> ret = new LinkedList<>();
        dfs(nums, 0, new ArrayList<>(), ret);
        return ret;
    }
    
    private void dfs(int[] nums, int index, List<Integer> curPath, List<List<Integer>> ret) {
        if (curPath.size() >= 2) {
            ret.add(new ArrayList<>(curPath));
        }
        
        for (int i = index; i < nums.length; i++) {
            if (i > index && indexOf(nums, nums[i], index, i - 1) >= 0) {
                continue;
            }
            
            if (curPath.isEmpty() || (curPath.get(curPath.size() - 1) <= nums[i])) {
                curPath.add(nums[i]);
                dfs(nums, i + 1, curPath, ret);
                curPath.remove(curPath.size() - 1);
            }
        }
    }
    
    private int indexOf(int[] nums, int target, int l, int r) {
        for (int i = l; i <= r; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }
}
