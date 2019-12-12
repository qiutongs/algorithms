class Solution {
    public int minMoves2(int[] nums) {
        int len = nums.length;
        int mid = findKth(nums, (len - 1) / 2); 
        int ret = 0;
        for (int i = 0; i < len; i++) {
            ret += Math.abs(mid - nums[i]);
        }
        return ret;
    }
    
    private int findKth(int[] nums, int k) {
        return findKth(nums, 0, nums.length - 1, k);
    }
    
    private int findKth(int[] nums, int l, int r, int k) {
        if (l == r) {
            return nums[l];
        }
        int pivot = partition(nums, l, r);
        if (pivot == k) {
            return nums[pivot];
        }
        
        if (k > pivot) {
            return findKth(nums, pivot + 1, r, k);
        } else {
            return findKth(nums, l, pivot - 1, k);
        }
    }
    
    private int partition(int[] nums, int l, int r) {
        int j = l;
        for (int i = l; i < r; i++) {
            if (nums[i] < nums[r]) {
                swap(nums, i, j);
                j++;
            }
        }
        swap(nums, j, r);
        return j;
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}