// If not handle duplicates, time Limit Exceed on [5,5,5,5,16,4,4,4,4,4,3,3,3,3,4]
class Solution {
    public boolean makesquare(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 4 != 0) {
            return false;
        }
        Arrays.sort(nums);
        return dfs(nums, 0, sum / 4, 0, 1);
    }
    
    private boolean dfs(int[] nums, int offset, int sideL, int curL, int curSide) {
        if (offset == nums.length) {
            return curL == 0 && curSide == 5;
        }
        if (curSide == 5) {
            return false;
        }
        for (int i = offset; i < nums.length; i++) {
            if (indexOf(nums, offset, i - 1, nums[i]) >= 0) {
                continue;
            }
            int l = curL + nums[i];
            if (l <= sideL) {
                swap(nums, i, offset);
                if (dfs(nums, offset + 1, sideL, l == sideL ? 0 : l, l == sideL ? curSide + 1 : curSide)) {
                    return true;
                }
                swap(nums, i, offset);
            }
        }
        return false;
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    
    private int indexOf(int[] nums, int l, int r, int target) {
        for(int i = l; i <= r; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }
}