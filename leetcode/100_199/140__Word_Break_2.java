class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> ret = new ArrayList<>();
        dfs(s, 0, new HashSet<>(wordDict), new HashSet<>(), new ArrayList<>(), ret);
        return ret;
    }
    
    private boolean dfs(String s, int offset, Set<String> wordSet, Set<String> failedSet, List<String> curList, List<String> ret) {
        if (offset == s.length()) {
            ret.add(output(curList));
            return true;
        }
        if (failedSet.contains(s.substring(offset, s.length()))) {
            return false;
        }
        boolean res = false;
        for (int i = offset; i < s.length(); i++) {
            String word = s.substring(offset, i + 1);
            if (wordSet.contains(word)) {
                curList.add(word);
                if (dfs(s, i + 1, wordSet, failedSet, curList, ret)) {
                    res = true;
                }
                curList.remove(curList.size() - 1);
            }
        }
        if (res == false) {
            failedSet.add(s.substring(offset, s.length()));
        }
        return res;
    }
    
    private String output(List<String> curList) {
        StringBuilder sb = new StringBuilder();
        for (String str : curList) {
            sb.append(str);
            sb.append(' ');
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}

// Raw DFS: Time Limit Exceeded
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> ret = new ArrayList<>();
        dfs(s, 0, new HashSet<>(wordDict), new ArrayList<>(), ret);
        return ret;
    }
    
    private void dfs(String s, int offset, Set<String> wordSet, List<String> curList, List<String> ret) {
        if (offset == s.length()) {
            ret.add(output(curList));
            return;
        }
        for (int i = offset; i < s.length(); i++) {
            String word = s.substring(offset, i + 1);
            if (wordSet.contains(word)) {
                curList.add(word);
                dfs(s, i + 1, wordSet, curList, ret);
                curList.remove(curList.size() - 1);
            }
        }
    }
    
    private String output(List<String> curList) {
        StringBuilder sb = new StringBuilder();
        for (String str : curList) {
            sb.append(str);
            sb.append(' ');
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}