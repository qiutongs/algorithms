// DFS + full memo
class Solution {
    private List<List<String>>[] memo;
    
    public List<String> wordBreak(String s, List<String> wordDict) {
        memo = new List[s.length()];
        List<List<String>> sentences = dfs(s, 0, new HashSet<>(wordDict));
        
        List<String> ret = new ArrayList<>();
        for (List<String> sentence : sentences) {
            ret.add(output(sentence));
        }
        return ret;
    }
    
    private List<List<String>> dfs(String s, int offset, Set<String> wordSet) {
        if (offset == s.length()) {
            return Arrays.asList(new ArrayList<>());
        }
        if (memo[offset] != null) {
            return memo[offset];
        }
        List<List<String>> ret = new ArrayList<>();
        for (int i = offset; i < s.length(); i++) {
            String word = s.substring(offset, i + 1);
            if (wordSet.contains(word)) {
                List<List<String>> subret = dfs(s, i + 1, wordSet);
                for (List<String> sentence : subret) {
                    List<String> newSentence = new ArrayList<>();
                    newSentence.add(word);
                    newSentence.addAll(sentence);
                    ret.add(newSentence);
                }
            }
        }
        memo[offset] = ret;
        return ret;
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

// DFS + true/false memo
class Solution {
    private Boolean[] memo;
    
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> ret = new ArrayList<>();
        memo = new Boolean[s.length()];
        dfs(s, 0, new HashSet<>(wordDict), new ArrayList<>(), ret);
        return ret;
    }
    
    private boolean dfs(String s, int offset, Set<String> wordSet, List<String> curList, List<String> ret) {
        if (offset == s.length()) {
            ret.add(output(curList));
            return true;
        }
        if (memo[offset] == Boolean.FALSE) {
            return false;
        }
        boolean result = false;
        for (int i = offset; i < s.length(); i++) {
            String word = s.substring(offset, i + 1);
            if (wordSet.contains(word)) {
                curList.add(word);
                if (dfs(s, i + 1, wordSet, curList, ret)) {
                    result = true;
                }
                curList.remove(curList.size() - 1);
            }
        }
        memo[offset] = result;
        return result;
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