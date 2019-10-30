class Solution {
    public int arrayPairSum(int[] nums) {
        countSort(nums);
        int ret = 0;
        for (int i = 0; i < nums.length; i++) {
            ret += i % 2 == 0 ? nums[i] : 0;
        }
        return ret;
    }
    
    private void countSort(int[] nums) {
        int[] count = new int[20001];
        
        for (int num : nums) {
            count[num + 10000]++;
        }
        
        int j = 0;
        for (int i = 0; i < 20001; i++) {
            while(count[i] > 0) {
                nums[j++] = i - 10000;
                count[i]--;
            }
        }
    }
}