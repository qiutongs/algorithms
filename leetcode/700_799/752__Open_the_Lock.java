class Solution {
    public int openLock(String[] deadends, String target) {
        HashSet<String> deadSet = new HashSet<>();
        for (String dead : deadends) {
            deadSet.add(dead);
        }

        Queue<String> q = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        q.offer("0000");
        visited.add("0000");
        
        int level = 0;
        while(q.isEmpty() == false) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String cur = q.poll();
                if (cur.equals(target)) {
                    return level;
                }
                if (deadSet.contains(cur) == false) {
                    for (String nb : getNeighbors(cur)) {
                        if (visited.contains(nb) == false) {
                            visited.add(nb);
                            q.offer(nb);
                        }
                    }
                }
            }
            level++;
        }
        return -1;
    }
    
    private List<String> getNeighbors(String cur) {
        List<String> ret = new ArrayList<>(8);
        char[] curArr = cur.toCharArray();
        for (int j = 0; j < curArr.length; j++) {
            char tmp = curArr[j];
                        
            curArr[j] = (char)(tmp + 1);
            curArr[j] = curArr[j] > '9' ? '0' : curArr[j];
            ret.add(new String(curArr));
            
            curArr[j] = (char)(tmp - 1);
            curArr[j] = curArr[j] < '0' ? '9' : curArr[j];
            ret.add(new String(curArr));
            
            curArr[j] = tmp;
        }
        return ret;
    }
}