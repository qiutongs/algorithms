// DFS + memo for all words
class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        if (words == null || words.length == 0) {
            return Collections.emptyList();
        }
        HashSet<String> wordsSet = new HashSet<>();
        for(String word : words) {
            wordsSet.add(word);
        }
        HashMap<String, Boolean> memo = new HashMap<>();
        List<String> ret = new ArrayList<>();
        for (String word : words) {
            if (word.isEmpty() == false && dfs(word, 0, wordsSet, memo)) {
                ret.add(word);
            }
        }
        return ret;
    }
    
    private boolean dfs(String word, int offset, HashSet<String> words, HashMap<String, Boolean> memo) {
        if (offset == word.length()) {
            return true;
        }
        String curword = word.substring(offset, word.length());
        if (offset > 0 && words.contains(curword)) {
            return true;
        }
        if (memo.containsKey(curword)) {
            return memo.get(curword);
        }
        for (int i = offset; i < word.length(); i++) {
            if (offset == 0 && i == word.length() - 1) {
                continue;
            }
            String subword = word.substring(offset, i + 1);
            if (words.contains(subword)) {
                if (dfs(word, i + 1, words, memo)) {
                    memo.put(curword, true);
                    return true;
                }
            }
        }
        memo.put(curword, false);
        return false;
    }
}

// DFS + memo for each word (Exactly same as Word Break 1)
// Not consider the relationship between each word
class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        if (words == null || words.length == 0) {
            return Collections.emptyList();
        }
        HashSet<String> wordsSet = new HashSet<>();
        for(String word : words) {
            wordsSet.add(word);
        }
        List<String> ret = new ArrayList<>();
        for (String word : words) {
            if (word.isEmpty() == false && dfs(word, 0, wordsSet, new Boolean[word.length()])) {
                ret.add(word);
            }
        }
        return ret;
    }
    
    private boolean dfs(String word, int offset, HashSet<String> words, Boolean[] memo) {
        if (offset == word.length()) {
            return true;
        }
        if (memo[offset] != null) {
            return memo[offset];
        }
        for (int i = offset; i < word.length(); i++) {
            if (offset == 0 && i == word.length() - 1) {
                continue;
            }
            String subword = word.substring(offset, i + 1);
            if (words.contains(subword)) {
                if (dfs(word, i + 1, words, memo)) {
                    memo[offset] = true;
                    return true;
                }
            }
        }
        memo[offset] = false;
        return false;
    }
}