class Solution1 {
    public List<List<Integer>> findSubsequences(int[] nums) {
        return findSubHelper(nums, nums.length)
                    .stream()
                    .distinct()
                    .filter(sublist -> sublist.size() >= 2)
                    .collect(Collectors.toList());
    }
    
    private List<List<Integer>> findSubHelper(int[] nums, int n) {
        List<List<Integer>> result = new ArrayList<>();

        if (n == 0) {
            result.add(Collections.emptyList());
            return result; 
        }
        
        List<List<Integer>> subResult = findSubHelper(nums, n - 1);
        
        for (List<Integer> sublist : subResult) {
            if (sublist.isEmpty() || nums[n - 1] >= sublist.get(sublist.size() - 1)) {
                List<Integer> newSublist = new ArrayList<>(sublist);
                newSublist.add(nums[n - 1]);
                result.add(newSublist);
            }
            
            result.add(sublist);
        }
        
        return result;
    }
}

class Solution2 {
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