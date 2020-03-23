// Time: put - O(logN), retrieve - O(in_range_result)
class LogSystem {
    private static final HashMap<String, Integer> GRA_IDX_MAP = new HashMap<>();
    static {
        GRA_IDX_MAP.put("Year", 0);
        GRA_IDX_MAP.put("Month", 1);
        GRA_IDX_MAP.put("Day", 2);
        GRA_IDX_MAP.put("Hour", 3);
        GRA_IDX_MAP.put("Minute", 4);
        GRA_IDX_MAP.put("Second", 5);
    }
    
    private final TreeMap<String, Integer> logs = new TreeMap<>();
    
    public LogSystem() {
        
    }
    
    public void put(int id, String timestamp) {
        logs.put(timestamp, id);
    }
    
    public List<Integer> retrieve(String s, String e, String gra) {
        String start = trim(s, gra, "00");
        String end = trim(e, gra, "59");
        return new ArrayList<>(logs.subMap(start, true, end, true).values());
    }
    
    private String trim(String timestamp, String gra, String padding) {
        int idx = GRA_IDX_MAP.get(gra);
        String[] strs = timestamp.split(":");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            if (i <= idx) {
                sb.append(strs[i]);
            } else {
                sb.append(padding);
            }
             sb.append(':');
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}

/**
 * Your LogSystem object will be instantiated and called as such:
 * LogSystem obj = new LogSystem();
 * obj.put(id,timestamp);
 * List<Integer> param_2 = obj.retrieve(s,e,gra);
 */