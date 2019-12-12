class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Arrays.asList(Collections.emptyList());
        }
        Arrays.sort(nums);
        List<List<Integer>> ret = new ArrayList<>();
        dfs(nums, new ArrayList<>(), new boolean[nums.length], ret);
        return ret;
    }
    
    private void dfs(int[] nums, List<Integer> curList, boolean[] visited, List<List<Integer>> ret) {
        if (curList.size() == nums.length) {
            ret.add(new ArrayList<>(curList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            if (i > 0 && visited[i - 1] == false && nums[i] == nums[i - 1]) {
                continue;
            }
            visited[i] = true;
            curList.add(nums[i]);
            dfs(nums, curList, visited, ret);
            curList.remove(curList.size() - 1);
            visited[i] = false;
        }
    }
}

class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
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
            if (indexOf(nums, offset, i - 1, nums[i]) >= 0) {
                continue;
            }
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
    
    private int indexOf(int[] nums, int l, int r, int target) {
        for (int i = l; i <= r; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }
}