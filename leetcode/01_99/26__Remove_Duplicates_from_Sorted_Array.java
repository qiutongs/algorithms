/*
- 2-way partitoon -> three regions:
  * region1 <= lastNotDup: no duplicates region
  * > lastNotDup < region2 < i: duplicates region
  * >= i: unexplored region
*/
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int l = 1;
        for (int r = 1; r < nums.length; r++) {
            if (nums[r] > nums[r - 1]) {
                nums[l] = nums[r];
                l++;
            }
        }
        return l;
    }
}
