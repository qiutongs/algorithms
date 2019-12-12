class MyHashMap {
    private static int SIZE = 1024;
    private final ArrayList<LinkedList<Pair>> table = new ArrayList<>(SIZE);
    
    /** Initialize your data structure here. */
    public MyHashMap() {
        for (int i = 0; i < SIZE; i++) {
            this.table.add(new LinkedList<>());
        }
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
        LinkedList<Pair> list = table.get(key % SIZE);
        for (Pair p : list) {
            if (p.key == key) {
                p.value = value;
                return;
            }
        }
        list.add(new Pair(key, value));
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        LinkedList<Pair> list = table.get(key % SIZE);
        for (Pair p : list) {
            if (p.key == key) {
                return p.value;
            }
        }
        return -1;
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        LinkedList<Pair> list = table.get(key % SIZE);
        for (Iterator<Pair> iter = list.iterator(); iter.hasNext();) {
            Pair p = iter.next();
            if (p.key == key) {
                iter.remove();
                break;
            }
        }
    }
    
    private class Pair {
        int key;
        int value;
        Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */