class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        long[] prefixSum = new long[nums.length + 1];
        for (int i = 1; i < prefixSum.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        return countAndSort(prefixSum, 0, prefixSum.length - 1, lower, upper);
    }
    
    private int countAndSort(long[] prefixSum, int l, int r, int lower, int upper) {
        if (l == r) {
            return 0;
        }
        
        int mid = (l + r) / 2;
        int leftCount = countAndSort(prefixSum, l, mid, lower, upper);
        int rightCount = countAndSort(prefixSum, mid + 1, r, lower, upper);
        return leftCount + rightCount + mergeCount(prefixSum, l, mid, r, lower, upper);
    }
    
    private int mergeCount(long[] prefixSum, int l, int mid, int r, int lower, int upper) {
        int ret = (mid - l + 1) * (r - mid);
        // count "> upper"
        int i1 = l, i2 = mid + 1;
        for (; i2 <= r; i2++) {
            while(i1 <= mid && prefixSum[i2] - prefixSum[i1] > upper) {
                i1++;
            }
            ret -= i1 - l;
        }
        // count "< lower"
        i1 = l;
        i2 = mid + 1;
        for (; i2 <= r; i2++) {
            while(i1 <= mid && prefixSum[i2] - prefixSum[i1] >= lower) {
                i1++;
            }
            ret -= mid - i1 + 1;
        }
        // merge
        long[] tmp = new long[r - l + 1];
        i1 = l;
        i2 = mid + 1;
        for (int i = 0; i < tmp.length; i++) {
            if (i1 > mid || (i2 <= r && prefixSum[i2] < prefixSum[i1])) {
                tmp[i] = prefixSum[i2++];
            } else {
                tmp[i] = prefixSum[i1++];
            }
        }
        for (int i = 0; i < tmp.length; i++) {
            prefixSum[l + i] = tmp[i];
        }
        return ret;
    }

    // version 2
    private int mergeCount(long[] prefixSum, int l, int mid, int r, int lower, int upper) {
        int ret = 0;
        // count "> upper"
        int i1 = l, i2 = mid + 1;
        for (; i2 <= r; i2++) {
            while(i1 <= mid && prefixSum[i2] - prefixSum[i1] > upper) {
                i1++;
            }
            int tmpL = i1;
            while(i1 <= mid && prefixSum[i2] - prefixSum[i1] >= lower) {
                i1++;
            }
            ret += i1 - tmpL;
            i1 = tmpL;
        }
        // merge
        long[] tmp = new long[r - l + 1];
        i1 = l;
        i2 = mid + 1;
        for (int i = 0; i < tmp.length; i++) {
            if (i1 > mid || (i2 <= r && prefixSum[i2] < prefixSum[i1])) {
                tmp[i] = prefixSum[i2++];
            } else {
                tmp[i] = prefixSum[i1++];
            }
        }
        for (int i = 0; i < tmp.length; i++) {
            prefixSum[l + i] = tmp[i];
        }
        return ret;
    }
}

