class Solution {
    public String addBoldTag(String s, String[] dict) {
        TrieNode root = new TrieNode();
        for (String word : dict) {
            insert(root, word);
        }
        List<int[]> intervals = generateIntervals(s, root);
        if (intervals.isEmpty()) {
            return s;
        } else {
            StringBuilder sb = new StringBuilder();
            if (intervals.get(0)[0] > 0) {
                sb.append(s.substring(0, intervals.get(0)[0]));
            }
            for (int i = 0; i < intervals.size(); i++) {
                int[] interval = intervals.get(i);
                if (i > 0) {
                    int l = intervals.get(i - 1)[1] + 1;
                    int r = interval[0];
                    sb.append(s.substring(l, r));
                }
                sb.append("<b>");
                sb.append(s.substring(interval[0], interval[1] + 1));
                sb.append("</b>");
            }
            if (intervals.get(intervals.size() - 1)[1] < s.length() - 1) {
                sb.append(s.substring(intervals.get(intervals.size() - 1)[1] + 1, s.length()));
            }
            return sb.toString();
        }
    }
    
    private List<int[]> generateIntervals(String s, TrieNode root) {
        List<int[]> intervals = new ArrayList<>();
        int[] cur = null;
        for (int i = 0; i < s.length(); i++) {
            int idx = search(root, s, i);
            if (idx >= 0) {
                if (cur == null) {
                    cur = new int[]{i, idx};
                } else if (i <= cur[1] + 1) {
                    cur[1] = Math.max(cur[1], idx);
                } else {
                    intervals.add(cur.clone());
                    cur[0] = i;
                    cur[1] = idx;
                }
            }
        }
        if (cur != null) {
            intervals.add(cur);
        }
        return intervals;
    }
    
    private int search(TrieNode root, String s, int idx) {
        TrieNode cur = root;
        int ret = -1;
        for (int i = idx; i < s.length(); i++) {
            char c = s.charAt(i);
            cur = cur.get(c);
            if (cur == null) {
                break;
            }
            if (cur.isKey) {
                ret = i;
            }
        }
        return ret;
    }
    
    private void insert(TrieNode root, String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            cur.addIfAbsent(c);
            cur = cur.get(c);
        }
        cur.isKey = true;
    }
    
    private class TrieNode {
        HashMap<Character, TrieNode> childs = new HashMap<>();
        boolean isKey = false;
        
        void addIfAbsent(char c) {
            if (childs.containsKey(c) == false) {
                childs.put(c, new TrieNode());
            }
        }
        TrieNode get(char c) {
            return childs.get(c);
        }
    }
}