// Time: 
// set - O(logM), M is the max number of value history for a key
// get - O(logM)
class TimeMap {
    private final HashMap<String, TreeMap<Integer, String>> datastore = new HashMap<>();
    
    /** Initialize your data structure here. */
    public TimeMap() {
        
    }
    
    public void set(String key, String value, int timestamp) {
        datastore.putIfAbsent(key, new TreeMap<>());
        datastore.get(key).put(timestamp, value);
    }
    
    public String get(String key, int timestamp) {
        if (datastore.containsKey(key)) {
            Map.Entry<Integer, String> ret = datastore.get(key).floorEntry(timestamp);
            return ret == null ? "" : ret.getValue();
        } else {
            return "";
        }
    }
}

// Time: 
// set - O(1), 
// get - O(logM) M is the max number of value history for a key
class TimeMap {
    private final HashMap<String, ArrayList<Data>> datastore = new HashMap<>();
    
    /** Initialize your data structure here. */
    public TimeMap() {
        
    }
    
    public void set(String key, String value, int timestamp) {
        datastore.putIfAbsent(key, new ArrayList<>());
        datastore.get(key).add(new Data(value, timestamp));
    }
    
    public String get(String key, int timestamp) {
        if (datastore.containsKey(key)) {
            ArrayList<Data> valueList = datastore.get(key);
            int pos = Collections.binarySearch(valueList, new Data("", timestamp));
            pos = pos < 0 ? -pos - 2 : pos;
            return pos < 0 ? "" : valueList.get(pos).value;
        } else {
            return "";
        }
    }
    
    private class Data implements Comparable<Data> {
        String value;
        int time;
        Data(String value, int time) {
            this.value = value;
            this.time = time;
        }
        public int compareTo(Data other) {
            return this.time - other.time;
        }
    }
}