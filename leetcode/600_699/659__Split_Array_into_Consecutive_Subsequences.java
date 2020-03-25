// DFS
// Time limit exceed
class Solution {
    public boolean isPossible(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }
        List<Pair> pairs = new ArrayList<>();
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                pairs.add(new Pair(nums[i - 1], count));
                count = 1;
            } else {
                count++;
            }
        }
        pairs.add(new Pair(nums[nums.length - 1], count));
        return dfs(pairs, new ArrayList<>(), nums.length);
    }
    
    private boolean dfs(List<Pair> pairs, List<Integer> curList, int n) {
        if (n == 0) {
            return curList.size() >= 3;
        }
        if (curList.size() >= 3 && dfs(pairs, new ArrayList<>(), n)) {
            return true;
        }
        int i = 0;
        while(i < pairs.size()) {
            if (pairs.get(i).count > 0 
                && (curList.isEmpty() || pairs.get(i).val == curList.get(curList.size() - 1) + 1)) {
                break;
            }
            i++;
        }
        if (i < pairs.size()) {
            pairs.get(i).count--;
            curList.add(pairs.get(i).val);
            if (dfs(pairs, curList, n - 1)) {
                return true;
            }
            curList.remove(curList.size() - 1);
            pairs.get(i).count++;
        }
        return false;
    }
    
    private class Pair {
        int val;
        int count;
        Pair(int val, int count) {
            this.val = val;
            this.count = count;
        }
    }
}