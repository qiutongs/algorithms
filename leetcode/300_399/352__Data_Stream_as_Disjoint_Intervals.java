// BST
// Time: addNum - O(logN), getIntervals - O(N)
class SummaryRanges {
    private TreeSet<int[]> intervals = new TreeSet<>((i1, i2) -> i1[0] - i2[0]);
    
    /** Initialize your data structure here. */
    public SummaryRanges() {
        
    }
    
    public void addNum(int val) {
        int[] newItvl = {val, val};
        int[] lesserIntvl = intervals.floor(newItvl);
        int[] greaterIntvl = intervals.higher(newItvl);
        
        if (lesserIntvl != null && lesserIntvl[1] >= val) {
            return;
        }
        
        if (lesserIntvl != null && lesserIntvl[1] == val - 1) {
            newItvl[0] = lesserIntvl[0];
            intervals.remove(lesserIntvl);
        }
        if (greaterIntvl != null && greaterIntvl[0] == val + 1) {
            newItvl[1] = greaterIntvl[1];
            intervals.remove(greaterIntvl);
        }
        intervals.add(newItvl);
    }
    
    public int[][] getIntervals() {
        return intervals.toArray(new int[0][]);
    }
}

// Unsorted list
// Time: addNum - O(1), getIntervals - O(NlogN)
class SummaryRanges {
    private ArrayList<Integer> vals = new ArrayList<>();
    
    /** Initialize your data structure here. */
    public SummaryRanges() {
        
    }
    
    public void addNum(int val) {
        vals.add(val);
    }
    
    public int[][] getIntervals() {
        ArrayList<int[]> ret = new ArrayList<>();
        
        Collections.sort(vals);
        int[] cur = {vals.get(0), vals.get(0)};
        for (int i = 1; i < vals.size(); i++) {
            if (vals.get(i) == vals.get(i - 1)) {
                continue;
            }
            if (vals.get(i) == cur[1] + 1) {
                cur[1] = vals.get(i);
            } else {
                ret.add(cur);
                cur = new int[]{vals.get(i), vals.get(i)};
            }
        }
        ret.add(cur);
        return ret.toArray(new int[0][]);
    }
}

// Wrong
// Hashtable: not consider the case that input val is within an existing interval
// Only storing start and end is not enough
class SummaryRanges {
    private HashMap<Integer, int[]> startItvlMap = new HashMap<>();
    private HashMap<Integer, int[]> endItvlMap = new HashMap<>();
    
    /** Initialize your data structure here. */
    public SummaryRanges() {
        
    }
    
    public void addNum(int val) {
        int[] lItvl = endItvlMap.get(val - 1);
        int[] rItvl = startItvlMap.get(val + 1);
        if (lItvl == null && rItvl == null) {
            int[] newItvl = {val, val};
            startItvlMap.put(val, newItvl);
            endItvlMap.put(val, newItvl);
        } else if (lItvl != null && rItvl == null) {
            lItvl[1] = val;
            endItvlMap.remove(val - 1);
            endItvlMap.put(val, lItvl);
        } else if (lItvl == null && rItvl != null) {
            rItvl[0] = val;
            startItvlMap.remove(val + 1);
            startItvlMap.put(val, rItvl);
        } else {
            lItvl[1] = rItvl[1];
            endItvlMap.put(rItvl[1], lItvl);
            endItvlMap.remove(val - 1);
            startItvlMap.remove(val + 1);
        }
    }
    
    public int[][] getIntervals() {
        Collection<int[]> list = startItvlMap.values();
        int[][] ret = new int[list.size()][];
        int i = 0;
        for (int[] itvl : list) {
            ret[i++] = itvl;
        }
        return ret;
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * int[][] param_2 = obj.getIntervals();
 */