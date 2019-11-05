// Ref https://segmentfault.com/a/1190000007138047#articleHeader4
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.compute(num, (key, v) -> v == null ? 1 : v + 1);
        }
        
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>((e1, e2) -> e1.getValue() - e2.getValue());
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (minHeap.size() < k) {
                minHeap.add(entry);
            } else {
                if (minHeap.peek().getValue() < entry.getValue()) {
                    minHeap.remove();
                    minHeap.add(entry);
                }
            }
        }
        
        Integer[] ret = new Integer[k];
        while(minHeap.isEmpty() == false) {
            ret[--k] = minHeap.poll().getKey();
        }
        return Arrays.asList(ret);
    }
}