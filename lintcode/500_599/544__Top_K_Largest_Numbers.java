public class Solution {
    /**
     * @param nums: an integer array
     * @param k: An integer
     * @return: the top k largest numbers in array
     */
    public int[] topk(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[] {};
        }
        int[] ret = new int[k];
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            if (minHeap.size() < k) {
                minHeap.offer(num);
            } else {
                if (minHeap.peek() < num) {
                    minHeap.poll();
                    minHeap.offer(num);
                }
            }
        }
        for (int i = ret.length - 1; i >= 0; i--) {
            ret[i] = minHeap.poll();
        }
        return ret;
    }
}