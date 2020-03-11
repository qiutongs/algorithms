int removeDuplicates(int* nums, int numsSize){
    if (numsSize == 0) {
        return 0;
    }
    int prev = nums[0];
    int l = 1, r = 1;
    for (; r < numsSize; r++) {
        if (nums[r] != prev) {
            nums[l] = nums[r];
            l++;
            prev = nums[r];
        }
    }
    return l;
}