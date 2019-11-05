void swap(int * nums, int i, int j) {
    int tmp = nums[j];
    nums[j] = nums[i];
    nums[i] = tmp;
}

int partition(int * nums, int l, int r) {
    int i = l, j = l;
    for (; j < r; j++) {
        if (nums[j] < nums[r]) {
            swap(nums, i, j);
            i++;
        }
    }
    swap(nums, i, r);
    return i;
}

void qHelper(int * nums, int l, int r) {
    if (l >= r) {
        return;
    }
    int pivot = partition(nums, l, r);
    qHelper(nums, l, pivot - 1);
    qHelper(nums, pivot + 1, r);
}

void qSort(int * nums, int size) {
    qHelper(nums, 0, size - 1);
}
/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* intersection(int* nums1, int nums1Size, int* nums2, int nums2Size, int* returnSize){
    int * ret = malloc((nums1Size > nums2Size ? nums2Size : nums1Size) * sizeof(int));
    int size = 0;
    qSort(nums1, nums1Size);
    qSort(nums2, nums2Size);
    
    int i1 = 0, i2 = 0;
    while(i1 < nums1Size && i2 < nums2Size) {
        if (i1 > 0 && nums1[i1] == nums1[i1 - 1]) {
            i1++;
            continue;
        }
        if (i2 > 0 && nums2[i2] == nums2[i2 - 1]) {
            i2++;
            continue;
        }
        if (nums1[i1] < nums2[i2]) {
            i1++;
        } else if (nums1[i1] > nums2[i2]) {
            i2++;
        } else {
            ret[size++] = nums1[i1];
            i1++;
            i2++;
        }
    }
    
    *returnSize = size;
    return ret;
}

int binarySearch(int * nums, int from, int to, int key) {
    int l = from, r = to - 1;
    while(l <= r) {
        int mid = (l + r) / 2;
        if (key < nums[mid]) {
            r = mid - 1;
        } else if (key > nums[mid]) {
            l = mid + 1;
        } else {
            return mid;
        }
    }
    return -(l + 1);
}

/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* intersection(int* nums1, int nums1Size, int* nums2, int nums2Size, int* returnSize){
    int * ret = malloc((nums1Size > nums2Size ? nums2Size : nums1Size) * sizeof(int));
    int size = 0;
    
    qSort(nums1, nums1Size);
    qSort(nums2, nums2Size);
    
    int * sNums = NULL, * lNums = NULL;
    int sSize = 0, lSize = 0;
    
    if (nums1Size < nums2Size) {
        sNums = nums1;
        sSize = nums1Size;
        lNums = nums2;
        lSize = nums2Size;
    } else {
        sNums = nums2;
        sSize = nums2Size;
        lNums = nums1;
        lSize = nums1Size;
    }
    
    int i = 0;
    int index = 0;
    for (; i < sSize; i++) {
        if (i > 0 && sNums[i] == sNums[i - 1]) {
            continue;
        }
        index = binarySearch(lNums, index, lSize, sNums[i]);
        if (index >= 0) {
            ret[size++] = sNums[i];
        }
        index = index < 0 ? -index - 1 : index;
    }
    
    *returnSize = size;
    return ret;
}

