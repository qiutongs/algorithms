class LFUCache {
    private HashMap<Integer, DataNode> hashTable = new HashMap();
    private FreqNode head = new FreqNode(null, null, 0);
    private int capacity;
    
    public LFUCache(int capacity) {
        this.capacity = capacity;
        head.next = head;
        head.prev = head;
    }
    
    public int get(int key) {
        DataNode dataNode = hashTable.get(key);
        if (dataNode == null) {
            return -1;
        }
        increaseByOne(dataNode);
        return dataNode.value;
    }
    
    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        DataNode dataNode = hashTable.get(key);
        if (dataNode == null) {
            if (hashTable.size() == capacity) {
                DataNode evictNode = head.next.dataList.pollFirst();
                hashTable.remove(evictNode.key);
                if (head.next.dataList.isEmpty()) {
                    removeFreqNode(head.next);
                }
            }
            if (head.next.freq != 1) {
                addFreqNode(head, 1);
            }
            dataNode = new DataNode(head.next, key, value);
            hashTable.put(key, dataNode);
            head.next.dataList.offerLast(dataNode);
        } else {
            increaseByOne(dataNode);
            dataNode.value = value;
        }
    }
    
    private void increaseByOne(DataNode dataNode) {
        FreqNode freqNode = dataNode.freqNode;
        if (freqNode.next.freq != freqNode.freq + 1) {
            addFreqNode(freqNode, freqNode.freq + 1);
        }
        freqNode.data.remove(dataNode);
        dataNode.freqNode = freqNode.next;
        freqNode.next.data.add(dataNode);
        if (freqNode.data.isEmpty()) {
            removeFreqNode(freqNode);
        }
    }
    
    private void addFreqNode(FreqNode node, int newFreq) {
        FreqNode ret = new FreqNode(node, node.next, newFreq);
        node.next.prev = ret;
        node.next = ret;
    }
    
    private void removeFreqNode(FreqNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    private class FreqNode {
        Deque<DataNode> dataList = new LinkedList<>();
        FreqNode prev;
        FreqNode next;
        int freq;
        FreqNode(FreqNode prev, FreqNode next, int freq) {
            this.prev = prev;
            this.next = next;
            this.freq = freq;
        }
    }
    
    private class DataNode {
        int key;
        int value;
        FreqNode freqNode;
        DataNode(FreqNode freqNode, int key, int value) {
            this.freqNode = freqNode;
            this.key = key;
            this.value = value;
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */