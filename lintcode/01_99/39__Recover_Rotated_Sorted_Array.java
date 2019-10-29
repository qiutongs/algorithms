public class Solution {
    /**
     * @param nums: An integer array
     * @return: nothing
     */
    public void recoverRotatedSortedArray(List<Integer> nums) {
        if (nums == null || nums.isEmpty()) {
            return;
        }
        
        Integer[] arr = nums.toArray(new Integer[0]);
        reverse(arr, 0, arr.length - 1);
        
        int mid = 1;
        while(mid < arr.length && arr[mid] <= arr[mid - 1]) {
            mid++;
        }
        
        reverse(arr, 0, mid - 1);
        reverse(arr, mid, arr.length - 1);
        
        nums.clear();
        for (Integer num : arr) {
            nums.add(num);
        }
    }
    
    private void reverse(Integer[] arr, int l, int r) {
        while(l < r) {
            int tmp = arr[l];
            arr[l++] = arr[r];
            arr[r--] = tmp;
        }
    }
}