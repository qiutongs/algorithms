class MyHashSet {
    private static int SIZE = 1024;
    private final ArrayList<LinkedList<Integer>> table = new ArrayList<>(SIZE);
    
    /** Initialize your data structure here. */
    public MyHashSet() {
        for (int i = 0; i < SIZE; i++) {
            this.table.add(new LinkedList<>());
        }
    }
    
    public void add(int key) {
        int offset = key % SIZE;
        LinkedList<Integer> list = table.get(offset);
        if (list.contains(key) == false) {
            list.addFirst(key);
        }
    }
    
    public void remove(int key) {
        int offset = key % SIZE;
        LinkedList<Integer> list = table.get(offset);
        Integer keyInt = key;
        list.remove(keyInt);
    }
    
    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int offset = key % SIZE;
        LinkedList<Integer> list = table.get(offset);
        return list.contains(key);
    }
    
    
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */