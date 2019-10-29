class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        int n = l1 + l2;
        
        if (n % 2 == 0) {
            return ((double)findKth(nums1, 0, nums2, 0, n / 2) 
                 + (double)findKth(nums1, 0, nums2, 0, n / 2 + 1)) / 2.0;
        } else {
            return (double)findKth(nums1, 0, nums2, 0, n / 2 + 1);
        }
    }
    
    private int findKth(int[] nums1, int index1, int[] nums2, int index2, int k) {
        if (index1 >= nums1.length) {
            return nums2[index2 + k - 1];
        }
        
        if (index2 >= nums2.length) {
            return nums1[index1 + k - 1];
        }
        
        if (k == 1) {
            return nums1[index1] < nums2[index2] ? nums1[index1] : nums2[index2];
        }
        
        // very important edge 
        // k is even: 1, 2, 3, 4; select left middle 2
        // k is odd: 1, 2, 3; select middle left 1
        int halfK = k / 2;
        int i1 = index1 + halfK - 1;
        int i2 = index2 + halfK - 1;
        
        int n1 = i1 < nums1.length ? nums1[i1] : Integer.MAX_VALUE;
        int n2 = i2 < nums2.length ? nums2[i2] : Integer.MAX_VALUE;

        if (n1 < n2) {
            return findKth(nums1, i1 + 1, nums2, index2, k - halfK);
        } else {
            return findKth(nums1, index1, nums2, i2 + 1, k - halfK);
        }
    }
}