class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.isEmpty() || wordList.indexOf(endWord) == -1) {
            return 0;
        }
        
        Map<String, List<String>> graph = buildGraph(beginWord, wordList);
        Set<String> visited = new HashSet<>();
        
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        visited.add(beginWord);
        
        int level = 0;
        
        while(queue.isEmpty() == false) {
            level++;
            
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                String str = queue.remove();
                
                if (str.equals(endWord)) {
                    return level;
                }
                
                for (String neighbor : graph.get(str)) {
                    if (visited.contains(neighbor) == false) {
                        queue.add(neighbor);
                        visited.add(neighbor);
                    }
                }
            }
        }
        
        return 0;
    }
    
    private Map<String, List<String>> buildGraph(String beginWord, List<String> wordList) {
        Map<String, List<String>> ret = new HashMap<>();
        
        for (String word : wordList) {
            ret.put(word, new LinkedList<>());
        }
        
        for (String word1 : wordList) {
            for (String word2: wordList) {
                if (word1 != word2) {
                    if (differByOne(word1, word2)) {
                        ret.get(word1).add(word2);
                    }
                }
            }
        }
        
        if (ret.containsKey(beginWord) == false) {
            ret.put(beginWord, new LinkedList<>());
            
            for (String word : wordList) {
                if (differByOne(word, beginWord)) {
                 ret.get(beginWord).add(word);
                }
            }
        }

        return ret;
    }
    
    boolean differByOne(String s1, String s2) {
        int diff = 0;
        
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diff++;
                
                if (diff > 1) {
                    break;
                }
            }
        }
        
        return diff == 1;
    }
}