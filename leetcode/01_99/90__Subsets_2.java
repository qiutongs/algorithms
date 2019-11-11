class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }
        Arrays.sort(nums);
        List<List<Integer>> ret = new ArrayList<>();
        dfs(nums, 0, new ArrayList<>(), ret);
        return ret;
    }
    
    private void dfs(int[] nums, int offset, List<Integer> curList, List<List<Integer>> ret) {
        ret.add(new ArrayList<>(curList));

        for (int i = offset; i < nums.length; i++) {
            if (i > offset && nums[i] == nums[i - 1]) {
                continue;
            }
            curList.add(nums[i]);
            dfs(nums, i + 1, curList, ret);
            curList.remove(curList.size() - 1);
        }
    }
}

/*
Idea: Sort and select next move carefully.
After sort, say there is a subarray of duplicated value: 2,2,2,2,2,2...
The output should only include ONCE '2', '22', '222', '2222', ....
1. if not select 2 on current index, then never, go to next different value
2. if select 2 on current index, we can go to next value (maybe duplicate)
*/
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>(1 << nums.length);

        Arrays.sort(nums);
        backtrack(new ArrayList<>(), nums.length - 1, nums, result);

        return result;
    }

    private void backtrack(List<Integer> curSet, int curIndex, int[] nums, List<List<Integer>> output) {
        if (curIndex == -1) {
            output.add(new ArrayList<>(curSet));
            return;
        }

        int nextIndex = curIndex;
        while(nextIndex >= 0 && nums[nextIndex] == nums[curIndex]) {
            nextIndex--;
        }

        // Not select current num.
        backtrack(curSet, nextIndex, nums, output);

        // Select current num.
        curSet.add(nums[curIndex]);
        backtrack(curSet, curIndex - 1, nums, output);
        curSet.remove(curSet.size() - 1);
    }
}


