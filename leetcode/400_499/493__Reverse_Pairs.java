// Divide and Conquer: merge sort and count
// Time: O(NlogN)
// Space: O(N)
class Solution {
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return countAndSort(nums, 0, nums.length - 1);
    }
    
    private int countAndSort(int[] nums, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = (l + r) / 2;
        int left = countAndSort(nums, l, mid);
        int right = countAndSort(nums, mid + 1, r);
        int rPairs = count(nums, l, mid, r);
        merge(nums, l, mid, r);
        return left + right + rPairs;
    }
    
    private int count(int[] nums, int l, int mid, int r) {
        int ret = 0;
        int p1 = l, p2 = mid + 1;
        while(p1 <= mid && p2 <= r) {
            if ((long)nums[p1] <= 2 * (long)nums[p2]) {
                p1++;
            } else {
                ret += mid - p1 + 1;
                p2++;
            }
        }
        return ret;
    }
    
    private void merge(int[] nums, int l, int mid, int r) {
        int[] tmp = new int[r - l + 1];
        int i = 0;
        
        int p1 = l, p2 = mid + 1;
        while(p1 <= mid && p2 <= r) {
            if (nums[p1] <= nums[p2]) {
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
            nums[l + j] = tmp[j];
        }
    }
}