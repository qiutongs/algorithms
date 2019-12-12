class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] ret = new int[nums1.length];
        HashMap<Integer, Integer> vnMap = new HashMap<>();
        Stack<Integer> deStack = new Stack<>();
        
        for (int i = 0; i < nums2.length; i++) {
            while(deStack.isEmpty() == false && deStack.peek() < nums2[i]) {
                int num = deStack.pop();
                vnMap.put(num, nums2[i]);
            }
            deStack.push(nums2[i]);
        }
        
        for (int i = 0; i < nums1.length; i++) {
            ret[i] = vnMap.containsKey(nums1[i]) ? vnMap.get(nums1[i]) : -1;
        }
        return ret;
    }
}