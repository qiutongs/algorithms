int removeDuplicates(int* nums, int numsSize){
    if (numsSize <= 1) {
        return numsSize;
    }
    int l = 2, r = 2;
    for (; r < numsSize; r++) {
        if (nums[r] != nums[l - 1] || nums[r] != nums[l - 2]) {
            nums[l] = nums[r];
            l++;
        }
    }
    return l;
}

