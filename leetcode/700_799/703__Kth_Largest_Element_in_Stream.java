/*
 * Maintain a min heap with size k
 * add takes O(logks)
 */
class KthLargest {

    private PriorityQueue<Integer> pq = new PriorityQueue<>();
    private int k;
    
    public KthLargest(int k, int[] nums) {
        this.k = k;
        
        Arrays.sort(nums);
        
        for (int i = Math.max(nums.length - k, 0); i < nums.length; i++) {
            pq.offer(nums[i]);
        }
    }
    
    public int add(int val) {
        if (pq.size() < k) {
            pq.offer(val);
        } else {
            if (val > pq.peek()) {
                pq.poll();
                pq.offer(val);
            }
        }

        return pq.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */