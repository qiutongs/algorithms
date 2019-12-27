public class Solution {
    private final PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private final int k;
    
    /*
    * @param k: An integer
    */
    public Solution(int k) {
        this.k = k;
    }

    /*
     * @param num: Number to be added
     * @return: nothing
     */
    public void add(int num) {
        if (k <= 0) {
            return;
        }
        if (minHeap.size() < k) {
            minHeap.offer(num);
        } else {
            if (minHeap.peek() < num) {
                minHeap.poll();
                minHeap.offer(num);
            }
        }
    }

    /*
     * @return: Top k element
     */
    public List<Integer> topk() {
        if (k <= 0) {
            return Collections.emptyList();
        }
        List<Integer> ret = new ArrayList<>(k);
        for (Integer num : minHeap) {
            ret.add(num);
        }
        Collections.sort(ret, Collections.reverseOrder());
        return ret;
    }
}