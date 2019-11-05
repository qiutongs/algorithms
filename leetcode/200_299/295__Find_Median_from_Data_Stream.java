class MedianFinder {
    private final PriorityQueue<Integer> lHeap = new PriorityQueue<>(Collections.reverseOrder());
    private final PriorityQueue<Integer> rHeap = new PriorityQueue<>();
    
    /** initialize your data structure here. */
    public MedianFinder() {
        
    }
    
    public void addNum(int num) {
        if (lHeap.isEmpty() || num <= lHeap.peek()) {
            lHeap.add(num);
        } else {
            rHeap.add(num);
        }
        if (lHeap.size() - rHeap.size() == 2) {
            rHeap.add(lHeap.remove());
        }
        if (rHeap.size() - lHeap.size() >= 1) {
            lHeap.add(rHeap.remove());
        }
    }
    
    public double findMedian() {
        int size = lHeap.size() + rHeap.size();
        if (size % 2 == 1) {
            return (double)lHeap.peek();
        } else {
            return ((double)lHeap.peek() + (double)rHeap.peek()) / 2.0;
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */