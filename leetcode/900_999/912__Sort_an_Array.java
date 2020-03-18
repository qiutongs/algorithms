// merge sort
class Solution {
    public int[] sortArray(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }
    
    private void mergeSort(int[] nums, int l, int r) {
        if (l == r) {
            return;
        }
        
        int mid = (l + r) / 2;
        mergeSort(nums, l, mid);
        mergeSort(nums, mid + 1, r);
        merge(nums, l, mid, r);
    }
    
    private void merge(int[] nums, int l, int mid, int r) {
        int[] tmp = new int[r - l + 1];
        int i = 0;
        
        int p1 = l, p2 = mid + 1;
        
        while(p1 <= mid && p2 <= r) {
            if (nums[p1] < nums[p2]) {
                tmp[i++] = nums[p1++];
            } else {
                tmp[i++] = nums[p2++];
            }
        }
        
        while(p1 <= mid) {
            tmp[i++] = nums[p1++];
        }
        
        while(p2 <= r) {
            tmp[i++] = nums[p2++];
        }
        
        for (int j = 0; j < tmp.length; j++) {
            nums[j + l] = tmp[j];
        }
    }
}