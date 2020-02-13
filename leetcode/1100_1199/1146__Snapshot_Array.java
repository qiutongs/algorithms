// List of hashtable to track delta in each snapshot: Optimize "set" and "snap"
class SnapshotArray {
    private final List<HashMap<Integer, Integer>> snapDelta = new ArrayList<>();
    private int snapCount = 0;
    
    public SnapshotArray(int length) {
        snapDelta.add(new HashMap<>());
    }
    
    // O(1)
    public void set(int index, int val) {
        snapDelta.get(snapCount).put(index, val);
    }
    
    // O(1)
    public int snap() {
        snapDelta.add(new HashMap<>());
        snapCount++;
        return snapCount - 1;
    }
    
    // O(SNAP)
    public int get(int index, int snap_id) {
        for (int i = snap_id; i >= 0; i--) {
            if (snapDelta.get(i).get(index) != null) {
                return snapDelta.get(i).get(index);
            }
        }
        return 0;
    }
}

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * SnapshotArray obj = new SnapshotArray(length);
 * obj.set(index,val);
 * int param_2 = obj.snap();
 * int param_3 = obj.get(index,snap_id);
 */

// List of hashtable to track delta in each snapshot: Optimize "set" and "get"
class SnapshotArray {
    private final List<HashMap<Integer, Integer>> snapDelta = new ArrayList<>();
    private int snapCount = 0;
    
    // O(1)
    public SnapshotArray(int length) {
        snapDelta.add(new HashMap<>());
    }
    
    // O(1)
    public void set(int index, int val) {
        snapDelta.get(snapCount).put(index, val);
    }
    
    // O(n)
    public int snap() {
        snapDelta.add(new HashMap<>(snapDelta.get(snapDelta.size() - 1)));
        snapCount++;
        return snapCount - 1;
    }
    
    // O(1)
    public int get(int index, int snap_id) {
        if (snapDelta.get(snap_id).get(index) != null) {
            return snapDelta.get(snap_id).get(index);
        } else {
            return 0;
        }
    }
}

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * SnapshotArray obj = new SnapshotArray(length);
 * obj.set(index,val);
 * int param_2 = obj.snap();
 * int param_3 = obj.get(index,snap_id);
 */

// Array of snap_id -> value map, optimized for snap, 
class SnapshotArray {
    private final TreeMap<Integer, Integer>[] snapDelta;
    private int snapCount = 0;
    
    public SnapshotArray(int length) {
        snapDelta = new TreeMap[length];
        for (int i = 0; i < length; i++) {
            snapDelta[i] = new TreeMap<>();
        }
    }
    
    // O(logn)
    public void set(int index, int val) {
        snapDelta[index].put(snapCount, val);
    }
    
    // O(1)
    public int snap() {
        snapCount++;
        return snapCount - 1;
    }
    
    // O(logn)
    public int get(int index, int snap_id) {
        Map.Entry<Integer, Integer> entry = snapDelta[index].floorEntry(snap_id);
        return entry == null ? 0: entry.getValue();
    }
}