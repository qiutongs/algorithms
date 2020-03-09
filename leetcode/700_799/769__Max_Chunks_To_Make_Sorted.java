class Solution {
    public int maxChunksToSorted(int[] arr) {
        int ret = 0;
        int max = -1;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            if (max == i) {
                ret++;
            }
        }
        return ret;
    }
}