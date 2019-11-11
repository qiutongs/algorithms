// https://www.geeksforgeeks.org/power-set/
// https://www.geeksforgeeks.org/recursive-program-to-generate-power-set/

// DFS
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null) {
            return Collections.emptyList();
        }
        List<List<Integer>> ret = new ArrayList<>();
        dfs(nums, 0, new ArrayList<>(), ret);
        return ret;
    }
    
    private void dfs(int[] nums, int offset, List<Integer> curList, List<List<Integer>> ret) {
        ret.add(new ArrayList<>(curList));
        
        for (int i = offset; i < nums.length; i++) {
            curList.add(nums[i]);
            dfs(nums, i + 1, curList, ret);
            curList.remove(curList.size() - 1);
        }
    }
}

// BFS
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null) {
            return Collections.emptyList();
        }
        List<List<Integer>> ret = new ArrayList<>();
        Queue<List<Integer>> q = new LinkedList<>();
        q.add(new ArrayList<>());
        
        while(q.isEmpty() == false) {
            List<Integer> node = q.poll();
            ret.add(node);
            int offset = node.isEmpty() ? 0 : indexOf(nums, node.get(node.size() - 1)) + 1;
            for (int i = offset; i < nums.length; i++) {
                List<Integer> neighbor = new ArrayList<>(node);
                neighbor.add(nums[i]);
                q.offer(neighbor);
            }
        }
        return ret;
    }
    
    private int indexOf(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }
}

// Div-and-Conq
// Note: this can be converted to iterative approach easily.
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        return subsets(nums, nums.length);
    }

    private List<List<Integer>> subsets(int[] nums, int n) {
        List<List<Integer>> ret = new ArrayList<>();

        if (n == 0) {
            result.add(new ArrayList<>());
            return result;
        }

        List<List<Integer>> subSubsets = subsets(nums, n - 1);
        ret.addAll(subSubsets);
        for (List<Integer> subSubset : subSubsets) {
            List<Integer> newSubset = new ArrayList<>(subSubset);
            newSubset.add(nums[n-1]);
            ret.add(newSubset);
        }

        return ret;
    }
}

// Most straightforward. Iterate all the 2^n subset.
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;

        List<List<Integer>> result = new ArrayList<>(1<<n);

        for (int i = 0; i < (1<<n); i++) {
            List<Integer> subset = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ( ((1 << j) & i) > 0 ) {
                    subset.add(nums[j]);
                }
            }
            result.add(subset);
        }

        return result;
    }
}

// pick one element or not
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>(1 << nums.length);
        backtrack(new ArrayList<>(), nums.length - 1, nums, result);
        return result;
    }

    private void backtrack(List<Integer> curSet, int curIndex, int[] nums, List<List<Integer>> output) {
        if (curIndex == -1) {
            output.add(new ArrayList<>(curSet));
            return;
        }

        // Not select current num.
        backtrack(curSet, curIndex - 1, nums, output);

        // Select current num.
        curSet.add(nums[curIndex]);
        backtrack(curSet, curIndex - 1, nums, output);
        curSet.remove(curSet.size() - 1);
    }
}

