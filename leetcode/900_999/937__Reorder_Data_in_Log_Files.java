// Sort
class Solution {
    public String[] reorderLogFiles(String[] logs) {
        int midIdx = partition(logs);
        
        Arrays.sort(logs, 0, midIdx, (let1, let2) -> {
            int i1 = let1.indexOf(' '), i2 = let2.indexOf(' ');
            String str1 = let1.substring(i1 + 1);
            String str2 = let2.substring(i2 + 1);
            if (str1.compareTo(str2) != 0) {
                return str1.compareTo(str2);
            } else {
                String id1 = let1.substring(0, i1);
                String id2 = let2.substring(0, i2);
                return id1.length() != id2.length() ? id1.length() - id2.length() : id1.compareTo(id2);
            }
        });
        
        return logs;
    }
    
    private int partition(String[] logs) {
        int l = logs.length - 1;
        for (int r = logs.length - 1; r >= 0; r--) {
            if (Character.isDigit(logs[r].charAt(logs[r].indexOf(' ') + 1))) {
                String tmp = logs[r];
                logs[r] = logs[l];
                logs[l] = tmp;
                l--;
            }
        }
        return l + 1;
    }
}