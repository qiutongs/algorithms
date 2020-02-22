// Brute force: pre-process each pair
// Time: WordDistance - O(N ^ 2), shortest - O(1) -> Time Limit Exceeded
// Space: O(N ^ 2)
// 
class WordDistance {
    private HashMap<String, HashMap<String, Integer>> wordsDisMap = new HashMap<>();
    
    public WordDistance(String[] words) {
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                wordsDisMap.putIfAbsent(words[i], new HashMap<>());
                HashMap<String, Integer> disMap = wordsDisMap.get(words[i]);
                if (disMap.containsKey(words[j]) == false || disMap.get(words[j]) > j - i) {
                    disMap.put(words[j], j - i);
                }
            }
        }
    }
    
    public int shortest(String word1, String word2) {
        int d1 = Integer.MAX_VALUE, d2 = Integer.MAX_VALUE;
        if (wordsDisMap.containsKey(word1) && wordsDisMap.get(word1).containsKey(word2)) {
            d1 = wordsDisMap.get(word1).get(word2);
        } 
        if (wordsDisMap.containsKey(word2) && wordsDisMap.get(word2).containsKey(word1)) {
            d2 = wordsDisMap.get(word2).get(word1);
        }
        return Math.min(d1, d2);
    }
}

// 2 Pointer on index lists
// Time: WordDistance - O(N), shortest - O(L), -> 25ms
// Space: O(N)
class WordDistance {
    private HashMap<String, List<Integer>> wordIndexesMap = new HashMap<>();
    
    public WordDistance(String[] words) {
        for (int i = 0; i < words.length; i++) {
            wordIndexesMap.putIfAbsent(words[i], new ArrayList<>());
            wordIndexesMap.get(words[i]).add(i);
        }
    }
    
    public int shortest(String word1, String word2) {
        int p1 = 0, p2 = 0;
        List<Integer> list1 = wordIndexesMap.get(word1);
        List<Integer> list2 = wordIndexesMap.get(word2);
        
        int ret = Integer.MAX_VALUE;
        while(p1 < list1.size() && p2 < list2.size()) {
            ret = Math.min(ret, Math.abs(list1.get(p1) - list2.get(p2)));
            if (list1.get(p1) < list2.get(p2)) {
                p1++;
            } else {
                p2++;
            }
        }
        return ret;
    }
}

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(words);
 * int param_1 = obj.shortest(word1,word2);
 */