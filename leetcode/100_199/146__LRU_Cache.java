class LRUCache {
    private final HashMap<Integer, Node> hashMap ;
    private final int capacity;
    private final Node head;
    
    public LRUCache(int capacity) {
        this.hashMap = new HashMap<>(capacity * 2);
        this.capacity = capacity;
        this.head = new Node(0, 0);
        this.head.previous = this.head;
        this.head.next = this.head;
    }
    
    public int get(int key) {
        Node node = hashMap.get(key);
        if (node == null) {
            return -1;
        } else {
            remove(node);
            append(node);
            return node.value;
        }
    }
    
    public void put(int key, int value) {
        Node node = hashMap.get(key);
        if (node == null) {
            if (hashMap.size() == capacity) {
                hashMap.remove(head.next.key);
                remove(head.next);
            }
            node = new Node(key, value);
            hashMap.put(key, node);
            append(node);
        } else {
            node.value = value;
            remove(node);
            append(node);
        }
    }
    
    private void remove(Node node) {
        node.next.previous = node.previous;
        node.previous.next = node.next;
        node.previous = null;
        node.next = null;
    }
    
    private void append(Node node) {
        node.next = head;
        node.previous = head.previous;
        head.previous.next = node;
        head.previous = node;
    }
    
    private class Node {
        int key;
        int value;
        Node previous;
        Node next;
        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.previous = null;
            this.next = null;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */


class LRUCache {
    private final LinkedHashMap<Integer, Integer> linkedHashMap;
    
    public LRUCache(int capacity) {
        this.linkedHashMap = new LinkedHashMap<Integer, Integer>(capacity * 2, 0.75F, true) {
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };
    }
    
    public int get(int key) {
        Integer value = linkedHashMap.get(key);
        return value == null ? -1 : value;
    }
    
    public void put(int key, int value) {
        linkedHashMap.put(key, value);
    }
}