/*
Time: O(n) average, O(n^2) worst

https://www.geeksforgeeks.org/kth-smallestlargest-element-unsorted-array-set-2-expected-linear-time/
*/
class Solution {
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
class Solution {
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

class Solution {
    public int findKthLargest(int[] nums, int k) {
        k = nums.length - k + 1;
        int l = getMin(nums), r = getMax(nums);
        while(l <= r) {
            int mid = (l + r) / 2;
            int[] counts = new int[2];
            getCount(nums, mid, counts);
            
            if (k <= counts[0]) {
                r = mid - 1;
            } else if (k <= counts[0] + counts[1]) {
                return mid;
            } else {
                l = mid + 1;
            }
        }
        throw new RuntimeException();
    }
    
    private void getCount(int[] nums, int target, int[] counts) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < target) {
                counts[0]++;
            } else if (nums[i] == target) {
                counts[1]++;
            }
        }
    }
    
    private int getMax(int[] nums) {
        int ret = nums[0];
        for (int i = 1; i < nums.length; i++) {
            ret = Math.max(ret, nums[i]);
        }
        return ret;
    }
    
    private int getMin(int[] nums) {
        int ret = nums[0];
        for (int i = 1; i < nums.length; i++) {
            ret = Math.min(ret, nums[i]);
        }
        return ret;
    }
}