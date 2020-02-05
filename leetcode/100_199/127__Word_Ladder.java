// Simpler way of building graph
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList.contains(endWord) == false) {
            return 0;
        }
        Map<String, List<String>> graph = buildGraph(beginWord, wordList);
        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        visited.add(beginWord);
        
        int level = 0;
        while (q.isEmpty() == false) {
            level++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String node = q.poll();
                if (node.equals(endWord)) {
                    return level;
                }
                for (String nb : graph.get(node)) {
                    if (visited.contains(nb) == false) {
                        q.offer(nb);
                        visited.add(nb);
                    }
                }
            }
        }
        return 0;
    }
    
    private Map<String, List<String>> buildGraph(String beginWord, List<String> wordList) {
        Map<String, List<String>> ret = new HashMap<>();
        wordList.add(beginWord);
        
        for (String word : wordList) {
            ret.put(word, new ArrayList<>());
        }
        
        for (String word : wordList) {
            char[] cArr = word.toCharArray();
            for (int i = 0; i < cArr.length; i++) {
                char cur = cArr[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    if (c != cur) {
                        cArr[i] = c;
                        String nb = new String(cArr);
                        if (ret.containsKey(nb)) {
                            ret.get(word).add(nb);
                        }
                    }
                }
                cArr[i] = cur;
            }
        }
        wordList.remove(wordList.size() - 1);
        return ret;
    }
}

// Bidirectional BFS
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList.contains(endWord) == false) {
            return 0;
        }
        Map<String, List<String>> graph = buildGraph(beginWord, wordList);
        Set<String> visited1 = new HashSet<>();
        Set<String> visited2 = new HashSet<>();
        Queue<String> q1 = new LinkedList<>();
        Queue<String> q2 = new LinkedList<>();
        q1.offer(beginWord);
        visited1.add(beginWord);
        q2.offer(endWord);
        visited2.add(endWord);
        
        int level1 = 0, level2 = 0;
        while (q1.isEmpty() == false && q2.isEmpty() == false) {
            level1++;
            int size1 = q1.size();
            for (int i = 0; i < size1; i++) {
                String node = q1.poll();
                if (visited2.contains(node)) {
                    return level1 + level2;
                }
                for (String nb : graph.get(node)) {
                    if (visited1.contains(nb) == false) {
                        q1.offer(nb);
                        visited1.add(nb);
                    }
                }
            }
            
            level2++;
            int size2 = q2.size();
            for (int i = 0; i < size2; i++) {
                String node = q2.poll();
                if (visited1.contains(node)) {
                    return level1 + level2;
                }
                for (String nb : graph.get(node)) {
                    if (visited2.contains(nb) == false) {
                        q2.offer(nb);
                        visited2.add(nb);
                    }
                }
            }
        }
        return 0;
    }
    
    private Map<String, List<String>> buildGraph(String beginWord, List<String> wordList) {
        Map<String, List<String>> ret = new HashMap<>();
        wordList.add(beginWord);
        
        for (String word : wordList) {
            ret.put(word, new ArrayList<>());
        }
        
        for (String word : wordList) {
            char[] cArr = word.toCharArray();
            for (int i = 0; i < cArr.length; i++) {
                char cur = cArr[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    if (c != cur) {
                        cArr[i] = c;
                        String nb = new String(cArr);
                        if (ret.containsKey(nb)) {
                            ret.get(word).add(nb);
                        }
                    }
                }
                cArr[i] = cur;
            }
        }
        wordList.remove(wordList.size() - 1);
        return ret;
    }
}

// Naive way of building graph
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