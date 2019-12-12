class Solution {
    public String removeDuplicates(String s, int k) {
        if (s == null || s.isEmpty() || k <= 0) {
            return s;
        }
        Entry[] entries = new Entry[s.length()];
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (j - 1 >= 0 && c == entries[j - 1].c) {
                if (entries[j - 1].count == k - 1) {
                    j--;
                } else {
                    entries[j - 1].count++;
                }
            } else {
                entries[j++] = new Entry(c);
            }
        }
        return toString(entries, j);
    }
    
    private String toString(Entry[] entries, int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < entries[i].count; j++) {
                sb.append(entries[i].c);
            }
        }
        return sb.toString();
    }
    
    private class Entry {
        char c;
        int count;
        Entry(char c) {
            this.c = c;
            this.count = 1;
        }
    }
}