/*
Time: O(n) average, O(n^2) worst

https://www.geeksforgeeks.org/kth-smallestlargest-element-unsorted-array-set-2-expected-linear-time/
*/
class Solution1 {
    private final Random rand = new Random();
    
    public int findKthLargest(int[] nums, int k) {
        return helper(nums, 0, nums.length - 1, nums.length - k);
    }
    
    private int helper(int[] nums, int l, int r, int k) {
        if (l == r) {
            return nums[l];
        }
        
        // pivot is in its sorted position
        int pivot = partition(nums, l, r);
        
        if (k < pivot) {
            return helper(nums, l, pivot - 1, k);
        } else if (k > pivot) {
            return helper(nums, pivot + 1, r, k);
        } else {
            return nums[pivot];
        }
    }
    
    private int partition(int[] nums, int l, int r) {
        int p = rand.nextInt(r - l + 1) + l;
        swap(nums, p, r);
        
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
class Solution2 {
    private final Random rand = new Random();
    
    public int findKthLargest(int[] nums, int k) {
        return helper(nums, 0, nums.length - 1, k);
    }
    
    private int helper(int[] nums, int l, int r, int k) {
        if (l == r) {
            return nums[l];
        }
        
        int mid = partition(nums, l, r);
        // ordinal of mid
        int midO = r - mid + 1;
        
        if (k < midO) {
            return helper(nums, mid + 1, r, k);
        } else if (k > midO) {
            return helper(nums, l, mid - 1, k - midO);
        } else {
            return nums[mid];
        }
    }
    
    private int partition(int[] nums, int l, int r) {
        int p = rand.nextInt(r - l + 1) + l;
        swap(nums, p, r);
        
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