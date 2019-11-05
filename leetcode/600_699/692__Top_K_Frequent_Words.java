class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> countMap = new HashMap<>();
        for (String word : words) {
            countMap.compute(word, (key, v) -> v == null ? 1 : v + 1);
        }
        
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>((e1, e2) -> {
            Integer diff = e1.getValue() - e2.getValue();
            if (diff != 0) {
                return diff;
            }
            return e2.getKey().compareTo(e1.getKey());
        });
                                                                                
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            minHeap.add(entry);
            if (minHeap.size() > k) {
                minHeap.remove();
            }
        }
        
        String[] ret = new String[k];
        while(minHeap.isEmpty() == false) {
            ret[--k] = minHeap.poll().getKey();
        }
        return Arrays.asList(ret);
    }
}