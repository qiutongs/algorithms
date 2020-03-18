class Solution {
    public List<Integer> countSmaller(int[] nums) {
        if (nums.length == 0) {
            return Collections.emptyList();
        }
        Integer[] ret = new Integer[nums.length];
        Element[] elements = new Element[nums.length];
        for (int i = 0; i < nums.length; i++) {
            elements[i] = new Element(i, nums[i]);
            ret[i] = 0;
        }

        mergeAndCount(elements, 0, nums.length - 1, ret);
        return Arrays.asList(ret);
    }
    
    private void mergeAndCount(Element[] elements, int l, int r, Integer[] counts) {
        if (l == r) {
            return;
        }
        int mid = (l + r) / 2;
        mergeAndCount(elements, l, mid, counts);
        mergeAndCount(elements, mid + 1, r, counts);
        merge(elements, l, mid, r, counts);
    }
    
    private void merge(Element[] elements, int l, int mid, int r, Integer[] counts) {
        Element[] tmp = new Element[r - l + 1];
        int tIdx = 0;
        
        int i = l, j = mid + 1;
        while(i <= mid && j <= r) {
            if (elements[i].val > elements[j].val) {
                counts[elements[i].idx] += r - j + 1;
                tmp[tIdx++] = elements[i++];
            } else {
                tmp[tIdx++] = elements[j++];
            }
        }
        while(i <= mid) {
            tmp[tIdx++] = elements[i++];
        }
        while(j <= r) {
            tmp[tIdx++] = elements[j++];
        }
        
        for (int k = 0; k < tmp.length; k++) {
            elements[l + k] = tmp[k];
        }
    }
    
    private class Element {
        int idx;
        int val;
        Element(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }
}