// Time Limit Exceed on [5,5,5,5,16,4,4,4,4,4,3,3,3,3,4]
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
        
        return dfs(nums, 0, sum / 4, 0, 1);
    }
    
    private boolean dfs(int[] nums, int index, int l, int curL, int side) {
        if (index == nums.length) {
            return curL == 0 && side == 5;
        }
        
        if (side > 4) {
            return false;
        }
        
        for (int i = index; i < nums.length; i++) {
            int toPlace = nums[i];
            if (curL + toPlace < l) {
                swap(nums, i, index);
                if (dfs(nums, index + 1, l, curL + toPlace, side)) {
                    return true;
                }
                swap(nums, i, index);
            } else if (curL + toPlace == l) {
                swap(nums, i, index);
                if (dfs(nums, index + 1, l, 0, side + 1)) {
                    return true;
                }
                swap(nums, i, index);
            }
        }
        
        return false;
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}