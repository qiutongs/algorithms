class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        ArrayList<Integer> ret = new ArrayList<>();
        
        int p1 = 0, p2 = 0;
        
        while(p1 < nums1.length && p2 < nums2.length) {
            if (p1 > 0 && nums1[p1] == nums1[p1 - 1]) {
                p1++;
                continue;
            }
            
            if (p2 > 0 && nums2[p2] == nums2[p2 - 1]) {
                p2++;
                continue;
            }
            
            if (nums1[p1] < nums2[p2]) {
                p1++;
            } else if (nums1[p1] > nums2[p2]) {
                p2++;
            } else {
                ret.add(nums1[p1]);
                p1++;
                p2++;
            }
        }
        
        int[] unboxedRet = new int[ret.size()];
        for (int i = 0; i < ret.size(); i++) {
            unboxedRet[i] = ret.get(i);
        }
        return unboxedRet;
    }
}

class Solution2 {
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> ret = new ArrayList<>();
        
        int[] sNums = nums1.length < nums2.length ? nums1 : nums2;
        int[] lNums = sNums == nums1 ? nums2 : nums1;
        int index = 0;
        for (int i = 0; i < sNums.length; i++) {
            if (i > 0 && sNums[i] == sNums[i - 1]) {
                continue;
            }
            index = Arrays.binarySearch(lNums, index, lNums.length, sNums[i]);
            if (index >= 0) {
                ret.add(sNums[i]);
            }
            index = index < 0 ? -index - 1 : index;
        }
        return toArray(ret);
    }
    
    private int[] toArray(List<Integer> list) {
        int[] ret = new int[list.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }
}