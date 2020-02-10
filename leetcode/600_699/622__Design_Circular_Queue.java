class MyCircularQueue {

    private final int[] data;
    private int size = 0;
    private int readIdx = 0, writeIdx = 0;
    
    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        data = new int[k];
    }
    
    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (size < data.length) {
            size++;
            data[writeIdx++] = value;
            writeIdx = writeIdx == data.length ? 0 : writeIdx;
            return true;
        } else {
            return false;
        }
    }
    
    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (size > 0) {
            size--;
            readIdx++;
            readIdx = readIdx == data.length ? 0 : readIdx;
            return true;
        } else {
            return false;
        }
    }
    
    /** Get the front item from the queue. */
    public int Front() {
        return size == 0 ? -1 : data[readIdx];
    }
    
    /** Get the last item from the queue. */
    public int Rear() {
        int lastIdx = writeIdx - 1;
        lastIdx = lastIdx < 0 ? data.length - 1 : lastIdx;
        return size == 0 ? -1 : data[lastIdx];
    }
    
    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return size == data.length;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */